package org.javacs.semantics;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.itsaky.lsp.SemanticHighlight;

import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.javacs.CompileTask;

public class SemanticHighlightProvider {
	
	private final CompileTask task;
	private final CancelChecker checker;
	
    public SemanticHighlightProvider(CompileTask task, CancelChecker checker) {
        this.task = task;
        this.checker = checker;
    }

    public List<SemanticHighlight> highlights() {
        var colors = new ArrayList<SemanticHighlight>(task.roots.size());
        for (int i = 0; i < task.roots.size(); i++) {
            final var root = task.roots.get(i);
            final var color = new SemanticHighlight();
            color.uri = root.getSourceFile().toUri().toString();
            try {
            	final var highlighter = new SemanticHighlighter(task, root, checker);
            	highlighter.scan (root, color);
            	if(!highlighter.getDocs().isEmpty()) {
            		for (var doc : highlighter.getDocs()) {
            			final var docHighlighter = new DocHighlighter(task, root, doc, checker);
            			docHighlighter.scan(doc, color.javadocs);
            		}
            	}
            } catch (Throwable th) {
            	th.printStackTrace();
            }
            
            colors.add(color);
        }
        return colors;
    }
    
    private static final Logger LOG = Logger.getLogger("main");
}