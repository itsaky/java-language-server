package org.javacs.markup;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;
import java.util.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

class DiagnosticVisitor extends TreeScanner<Void, Map<TreePath, String>> {
    // Copied from TreePathScanner
    // We need to be able to call scan(path, _) recursively
    private TreePath path;
    private final JavacTask task;
    private CompilationUnitTree root;
    private Map<String, TreePath> declaredExceptions = new HashMap<>();
    private Set<String> observedExceptions = new HashSet<>();
    
    private void scanPath(TreePath path) {
        TreePath prev = this.path;
        this.path = path;
        try {
            path.getLeaf().accept(this, null);
        } finally {
            this.path = prev; // So we can call scan(path, _) recursively
        }
    }

    @Override
    public Void scan(Tree tree, Map<TreePath, String> p) {
        if (tree == null) return null;
        
        TreePath prev = path;
        path = new TreePath(path, tree);
        try {
            return tree.accept(this, p);
        } finally {
            path = prev;
        }
    }

    private final Trees trees;
    private final Map<Element, TreePath> privateDeclarations = new HashMap<>(), localVariables = new HashMap<>();
    private final Set<Element> used = new HashSet<>();

    DiagnosticVisitor(JavacTask task) {
        this.task = task;
        this.trees = Trees.instance(task);
    }

    Set<Element> notUsed() {
        var unused = new HashSet<Element>();
        unused.addAll(privateDeclarations.keySet());
        unused.addAll(localVariables.keySet());
        unused.removeAll(used);
        // Remove if there are any null elements somehow ended up being added
        // during async work which calls `lint`
        unused.removeIf(Objects::isNull);
        // Remove if <error > field was injected while forming the AST
        unused.removeIf(i -> i.toString().equals("<error>"));
        return unused;
    }

    private void foundPrivateDeclaration() {
        privateDeclarations.put(trees.getElement(path), path);
    }

    private void foundLocalVariable() {
        localVariables.put(trees.getElement(path), path);
    }

    private void foundReference() {
        var toEl = trees.getElement(path);
        if (toEl == null) {
            return;
        }
        if (toEl.asType().getKind() == TypeKind.ERROR) {
            foundPseudoReference(toEl);
            return;
        }
        sweep(toEl);
    }

    private void foundPseudoReference(Element toEl) {
        var parent = toEl.getEnclosingElement();
        if (!(parent instanceof TypeElement)) {
            return;
        }
        var memberName = toEl.getSimpleName();
        var type = (TypeElement) parent;
        for (var member : type.getEnclosedElements()) {
            if (member.getSimpleName().contentEquals(memberName)) {
                sweep(member);
            }
        }
    }

    private void sweep(Element toEl) {
        var firstUse = used.add(toEl);
        var notScanned = firstUse && privateDeclarations.containsKey(toEl);
        if (notScanned) {
            scanPath(privateDeclarations.get(toEl));
        }
    }

    private boolean isReachable(TreePath path) {
        // Check if t is reachable because it's public
        var t = path.getLeaf();
        if (t instanceof VariableTree) {
            var v = (VariableTree) t;
            var isPrivate = v.getModifiers().getFlags().contains(Modifier.PRIVATE);
            if (!isPrivate || isLocalVariable(path)) {
                return true;
            }
        }
        if (t instanceof MethodTree) {
            var m = (MethodTree) t;
            var isPrivate = m.getModifiers().getFlags().contains(Modifier.PRIVATE);
            var isEmptyConstructor = m.getParameters().isEmpty() && m.getReturnType() == null;
            if (!isPrivate || isEmptyConstructor) {
                return true;
            }
        }
        if (t instanceof ClassTree) {
            var c = (ClassTree) t;
            var isPrivate = c.getModifiers().getFlags().contains(Modifier.PRIVATE);
            if (!isPrivate) {
                return true;
            }
        }
        // Check if t has been referenced by a reachable element
        var el = trees.getElement(path);
        return used.contains(el);
    }

    private boolean isLocalVariable(TreePath path) {
        var kind = path.getLeaf().getKind();
        if (kind != Tree.Kind.VARIABLE) {
            return false;
        }
        var parent = path.getParentPath().getLeaf().getKind();
        if (parent == Tree.Kind.CLASS || parent == Tree.Kind.INTERFACE) {
            return false;
        }
        if (parent == Tree.Kind.METHOD) {
            var method = (MethodTree) path.getParentPath().getLeaf();
            if (method.getBody() == null) {
                return false;
            }
        }
        return true;
    }
    
    private Map<String, TreePath> declared(MethodTree t) {
        var names = new HashMap<String, TreePath>();
        for (var e : t.getThrows()) {
            var path = new TreePath(this.path, e);
            var to = trees.getElement(path);
            if (!(to instanceof TypeElement)) continue;
            var type = (TypeElement) to;
            var name = type.getQualifiedName().toString();
            names.put(name, path);
        }
        return names;
    }
    
    @Override
    public Void visitCompilationUnit(CompilationUnitTree t, Map<TreePath, String> notThrown) {
        root = t;
        return super.visitCompilationUnit(t, notThrown);
    }

    @Override
    public Void visitVariable(VariableTree t, Map<TreePath, String> notThrown) {
        if (isLocalVariable(path)) {
            foundLocalVariable();
            super.visitVariable(t, notThrown);
        } else if (isReachable(path)) {
            super.visitVariable(t, notThrown);
        } else {
            foundPrivateDeclaration();
        }
        return null;
    }

    @Override
    public Void visitMethod(MethodTree t, Map<TreePath, String> notThrown) {
        // Create a new method scope
        var pushDeclared = declaredExceptions;
        var pushObserved = observedExceptions;
        declaredExceptions = declared(t);
        observedExceptions = new HashSet<>();
        // Recursively scan for 'throw' and method calls
        super.visitMethod(t, notThrown);
        // Check for exceptions that were never thrown
        for (var exception : declaredExceptions.keySet()) {
            if (!observedExceptions.contains(exception)) {
                notThrown.put(declaredExceptions.get(exception), exception);
            }
        }
        declaredExceptions = pushDeclared;
        observedExceptions = pushObserved;
        
        if (!isReachable(path)) {
            foundPrivateDeclaration();
        }
        return null;
    }

    @Override
    public Void visitClass(ClassTree t, Map<TreePath, String> notThrown) {
        if (isReachable(path)) {
            super.visitClass(t, notThrown);
        } else {
            foundPrivateDeclaration();
        }
        return null;
    }

    @Override
    public Void visitIdentifier(IdentifierTree t, Map<TreePath, String> notThrown) {
        foundReference();
        return super.visitIdentifier(t, notThrown);
    }

    @Override
    public Void visitMemberSelect(MemberSelectTree t, Map<TreePath, String> notThrown) {
        foundReference();
        return super.visitMemberSelect(t, notThrown);
    }

    @Override
    public Void visitMemberReference(MemberReferenceTree t, Map<TreePath, String> notThrown) {
        foundReference();
        return super.visitMemberReference(t, notThrown);
    }

    @Override
    public Void visitNewClass(NewClassTree t, Map<TreePath, String> notThrown) {
        foundReference();
        return super.visitNewClass(t, notThrown);
    }
    
    @Override
    public Void visitThrow(ThrowTree t, Map<TreePath, String> notThrown) {
        var path = new TreePath(this.path, t.getExpression());
        var type = trees.getTypeMirror(path);
        addThrown(type);
        return super.visitThrow(t, notThrown);
    }
    
    @Override
    public Void visitMethodInvocation(MethodInvocationTree t, Map<TreePath, String> notThrown) {
        var target = trees.getElement(this.path);
        if (target instanceof ExecutableElement) {
            var method = (ExecutableElement) target;
            for (var type : method.getThrownTypes()) {
                addThrown(type);
            }
        }
        return super.visitMethodInvocation(t, notThrown);
    }

    private void addThrown(TypeMirror type) {
        if (type instanceof DeclaredType) {
            var declared = (DeclaredType) type;
            var el = (TypeElement) declared.asElement();
            var name = el.getQualifiedName().toString();
            observedExceptions.add(name);
        }
    }
}