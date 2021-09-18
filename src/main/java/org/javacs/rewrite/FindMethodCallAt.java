package org.javacs.rewrite;

import java.util.logging.Logger;

import javax.lang.model.type.TypeKind;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.*;

class FindMethodCallAt extends TreePathScanner<MethodInvocationTree, Integer> {
    private final Trees trees;
    private final SourcePositions pos;
    private CompilationUnitTree root;
    private String assigningType;

    FindMethodCallAt(JavacTask task) {
        this.trees = Trees.instance(task);
        this.pos = trees.getSourcePositions();
    }
    
    public String getAssigningType(){
    	return assigningType;
    }

    @Override
    public MethodInvocationTree visitCompilationUnit(CompilationUnitTree t, Integer find) {
        root = t;
        return super.visitCompilationUnit(t, find);
    }

    @Override
    public MethodInvocationTree visitMethodInvocation(MethodInvocationTree t, Integer find) {
        var smaller = super.visitMethodInvocation(t, find);
        if (smaller != null) {
            return smaller;
        }
        if (pos.getStartPosition(root, t) <= find && find < pos.getEndPosition(root, t)) {
            return t;
        }
        return null;
    }
    
    @Override
    public MethodInvocationTree visitVariable(VariableTree tree, Integer find) {
    	if(tree != null) {
    		var start = pos.getStartPosition(root, tree);
    		var end = pos.getEndPosition(root, tree);
    		if(start <= find && find <= end && tree.getInitializer() instanceof MethodInvocationTree) {
    			assigningType = tree.getType().toString();
    			return visitMethodInvocation((MethodInvocationTree) tree.getInitializer(), find);
    		}
    	}
		return super.visitVariable(tree, find);
    }
    
    @Override
    public MethodInvocationTree visitAssignment(AssignmentTree tree, Integer find) {
    	if(tree != null) {
    		var start = pos.getStartPosition(root, tree);
    		var end = pos.getEndPosition(root, tree);
    		if(start <= find && find <= end && tree.getExpression() instanceof MethodInvocationTree) {
    			assigningType = findType(tree);
    			return visitMethodInvocation((MethodInvocationTree) tree.getExpression(), find);
    		}
    	}
		return super.visitAssignment(tree, find);
    	
    }

    private String findType(AssignmentTree tree) {
    	if(this.trees != null && getCurrentPath() != null) {
    		var typeMirror = this.trees.getTypeMirror(getCurrentPath());
    		if(typeMirror != null
    		&& typeMirror.getKind() != TypeKind.NONE 
    		&& typeMirror.getKind() != TypeKind.ERROR)
    			return typeMirror.toString();
    	}
		return null;
	}

	@Override
    public MethodInvocationTree reduce(MethodInvocationTree r1, MethodInvocationTree r2) {
        if (r1 != null) return r1;
        return r2;
    }
    
    private static final Logger LOG = Logger.getLogger("main");
}
