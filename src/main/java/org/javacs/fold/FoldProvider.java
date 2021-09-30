package org.javacs.fold;

import com.sun.source.tree.*;
import com.sun.source.util.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.FoldingRange;
import org.eclipse.lsp4j.FoldingRangeKind;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.javacs.CompilerProvider;
import org.javacs.ParseTask;

public class FoldProvider {

    final CompilerProvider compiler;

    public FoldProvider(CompilerProvider compiler) {
        this.compiler = compiler;
    }

    public List<FoldingRange> foldingRanges(CancelChecker checker, Path file) {
        var task = compiler.parse(file);
        checker.checkCanceled();
        var imports = new ArrayList<TreePath>();
        var blocks = new ArrayList<TreePath>();
        // TODO find comment trees
        var comments = new ArrayList<TreePath>();
        class FindFoldingRanges extends TreePathScanner<Void, Void> {
            @Override
            public Void visitClass(ClassTree t, Void __) {
                checker.checkCanceled();
                blocks.add(getCurrentPath());
                return super.visitClass(t, null);
            }

            @Override
            public Void visitBlock(BlockTree t, Void __) {
                checker.checkCanceled();
                blocks.add(getCurrentPath());
                return super.visitBlock(t, null);
            }

            @Override
            public Void visitImport(ImportTree t, Void __) {
                checker.checkCanceled();
                imports.add(getCurrentPath());
                return null;
            }
        }
        new FindFoldingRanges().scan(task.root, null);

        var all = new ArrayList<FoldingRange>();

        // Merge import ranges
        if (!imports.isEmpty()) {
            var merged = asFoldingRange(task, imports.get(0), FoldingRangeKind.Imports);
            for (var i : imports) {
                checker.checkCanceled();
                var r = asFoldingRange(task, i, FoldingRangeKind.Imports);
                if (r.getStartLine() <= merged.getEndLine() + 1) {
                    merged = new FoldingRange();
                    merged.setStartCharacter(merged.getStartCharacter());
                    merged.setStartLine(merged.getStartLine());
                    merged.setEndLine(r.getEndLine());
                    merged.setEndCharacter(r.getEndCharacter());
                    merged.setKind(FoldingRangeKind.Imports);
                } else {
                    all.add(merged);
                    merged = r;
                }
            }
            all.add(merged);
        }

        // Convert blocks and comments
        for (var t : blocks) {
            checker.checkCanceled();
            all.add(asFoldingRange(task, t, FoldingRangeKind.Region));
        }
        for (var t : comments) {
            checker.checkCanceled();
            all.add(asFoldingRange(task, t, FoldingRangeKind.Region));
        }

        return all;
    }

    private FoldingRange asFoldingRange(ParseTask task, TreePath t, String kind) {
        var trees = Trees.instance(task.task);
        var pos = trees.getSourcePositions();
        var lines = t.getCompilationUnit().getLineMap();
        var start = (int) pos.getStartPosition(t.getCompilationUnit(), t.getLeaf());
        var end = (int) pos.getEndPosition(t.getCompilationUnit(), t.getLeaf());

        // If this is a class tree, adjust start position to '{'
        if (t.getLeaf() instanceof ClassTree) {
            CharSequence content;
            try {
                content = t.getCompilationUnit().getSourceFile().getCharContent(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (var i = start; i < content.length(); i++) {
                if (content.charAt(i) == '{') {
                    start = i;
                    break;
                }
            }
        }

        // Convert offset to 0-based line and character
        var startLine = (int) lines.getLineNumber(start) - 1;
        var startChar = (int) lines.getColumnNumber(start) - 1;
        var endLine = (int) lines.getLineNumber(end) - 1;
        var endChar = (int) lines.getColumnNumber(end) - 1;

        // If this is a block, move end position back one line so we don't fold the '}'
        if (t.getLeaf() instanceof ClassTree || t.getLeaf() instanceof BlockTree) {
            endLine--;
        }
        
        var range = new FoldingRange();
        range.setStartLine(startLine);
        range.setStartCharacter(startChar);
        range.setEndLine(endLine);
        range.setEndCharacter(endChar);
        range.setKind(kind);
        return range;
    }
}
