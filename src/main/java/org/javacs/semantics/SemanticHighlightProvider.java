package org.javacs.semantics;

import java.util.ArrayList;
import java.util.List;

import com.itsaky.lsp.SemanticHighlight;

import org.javacs.CompileTask;

public class SemanticHighlightProvider {
	
	final CompileTask task;

    public SemanticHighlightProvider(CompileTask task) {
        this.task = task;
    }

    public List<SemanticHighlight> highlights() {
        var colors = new ArrayList<SemanticHighlight>(task.roots.size());
        for (int i = 0; i < task.roots.size(); i++) {
            var root = task.roots.get(i);
            var color = new SemanticHighlight();
            color.uri = root.getSourceFile().toUri().toString();
            new SemanticHighlighter(task.task).scan(root, color);
            
            colors.add(color);
        }
        return colors;
    }
}