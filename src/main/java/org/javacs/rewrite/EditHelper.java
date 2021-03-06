/************************************************************************************
 * This file is part of Java Language Server (https://github.com/itsaky/java-language-server)
 *
 * Copyright (C) 2021 Akash Yadav
 *
 * Java Language Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Java Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Java Language Server.  If not, see <https://www.gnu.org/licenses/>.
 *
**************************************************************************************/

package org.javacs.rewrite;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;
import java.util.List;
import java.util.StringJoiner;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import org.eclipse.lsp4j.*;

class EditHelper {
    final JavacTask task;

    EditHelper(JavacTask task) {
        this.task = task;
    }

    TextEdit removeTree(final CompilationUnitTree root, final Tree remove) {
        var pos = Trees.instance(task).getSourcePositions();
        var lines = root.getLineMap();
        var start = pos.getStartPosition(root, remove);
        var end = pos.getEndPosition(root, remove);
        var startLine = (int) lines.getLineNumber(start);
        var startColumn = (int) lines.getColumnNumber(start);
        var startPos = new Position(startLine - 1, startColumn - 1);
        var endLine = (int) lines.getLineNumber(end);
        var endColumn = (int) lines.getColumnNumber(end);
        var endPos = new Position(endLine - 1, endColumn - 1);
        var range = new Range(startPos, endPos);
        return new TextEdit(range, "");
    }

    static String printMethod(final ExecutableElement method, final ExecutableType parameterizedType, MethodTree source) {
        var buf = new StringBuilder();
        // TODO leading \n is extra, but needed for indent replaceAll trick
        buf.append("\n@Override\n");
        if (method.getModifiers().contains(Modifier.PUBLIC)) {
            buf.append("public ");
        }
        if (method.getModifiers().contains(Modifier.PROTECTED)) {
            buf.append("protected ");
        }
        buf.append(EditHelper.printType(parameterizedType.getReturnType())).append(" ");
        buf.append(method.getSimpleName()).append("(");
        buf.append(printParameters(parameterizedType, source));
        buf.append(") {\n    // TODO\n}");
        return buf.toString();
    }

    private static String printParameters(final ExecutableType method, final MethodTree source) {
        var join = new StringJoiner(", ");
        for (var i = 0; i < method.getParameterTypes().size(); i++) {
            var type = EditHelper.printType(method.getParameterTypes().get(i));
            var name = source.getParameters().get(i).getName();
            join.add(type + " " + name);
        }
        return join.toString();
    }

    static String printType(final TypeMirror type) {
        if (type instanceof DeclaredType) {
            var declared = (DeclaredType) type;
            var string = printTypeName((TypeElement) declared.asElement());
            if (!declared.getTypeArguments().isEmpty()) {
                string = string + "<" + printTypeParameters(declared.getTypeArguments()) + ">";
            }
            return string;
        } else if (type instanceof ArrayType) {
            var array = (ArrayType) type;
            return printType(array.getComponentType()) + "[]";
        } else {
            return type.toString();
        }
    }

    private static String printTypeParameters(final List<? extends TypeMirror> arguments) {
        var join = new StringJoiner(", ");
        for (var a : arguments) {
            join.add(printType(a));
        }
        return join.toString();
    }

    static String printTypeName(final TypeElement type) {
        if (type.getEnclosingElement() instanceof TypeElement) {
            return printTypeName((TypeElement) type.getEnclosingElement()) + "." + type.getSimpleName();
        }
        return type.getSimpleName().toString();
    }

    static int indent(final JavacTask task, final CompilationUnitTree root, final ClassTree leaf) {
        var pos = Trees.instance(task).getSourcePositions();
        var lines = root.getLineMap();
        var startClass = pos.getStartPosition(root, leaf);
        var startLine = lines.getStartPosition(lines.getLineNumber(startClass));
        return (int) (startClass - startLine);
    }
    
    static int indent (final String contents, final int cursor) {
    	int indent = 0;
    	for (var i=0;i<=cursor && i<contents.length();i++) {
    		var c = contents.charAt(i);
    		if(c == '{') {
    			indent++;
    		} else if (c == '}') {
    			indent--;
    		}
    	}
    	return indent * 4;
    }

    static Position insertBefore(final JavacTask task, final CompilationUnitTree root, final Tree member) {
        var pos = Trees.instance(task).getSourcePositions();
        var lines = root.getLineMap();
        var start = pos.getStartPosition(root, member);
        var line = (int) lines.getLineNumber(start);
        return new Position(line - 1, 0);
    }

    static Position insertAfter(final JavacTask task, final CompilationUnitTree root, final Tree member) {
        var pos = Trees.instance(task).getSourcePositions();
        var lines = root.getLineMap();
        var end = pos.getEndPosition(root, member);
        var line = (int) lines.getLineNumber(end);
        return new Position(line, 0);
    }

    static Position insertAtEndOfClass(JavacTask task, CompilationUnitTree root, ClassTree leaf) {
        var pos = Trees.instance(task).getSourcePositions();
        var lines = root.getLineMap();
        var end = pos.getEndPosition(root, leaf);
        var line = (int) lines.getLineNumber(end);
        var column = (int) lines.getColumnNumber(end);
        return new Position(line - 1, column - 2);
    }
}
