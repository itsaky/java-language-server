package org.javacs.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.itsaky.lsp.SemanticHighlightsParams;
import com.itsaky.lsp.SemanticHighlight;

import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;
import org.eclipse.lsp4j.services.TextDocumentService;

public interface JavaTextDocumentService extends TextDocumentService {
	
	@JsonRequest ("semanticHighlights")
	CompletableFuture<List<SemanticHighlight>> semanticHighlights (SemanticHighlightsParams params) ;
	
}