package org.javacs;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.itsaky.lsp.SemanticHighlight;
import com.itsaky.lsp.services.IDELanguageClient;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.RegistrationParams;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.javacs.services.JavaLanguageServer;

public class LanguageServerProvider {
	
	private static final Logger LOG = Logger.getLogger("main");
	
	static {
		Main.setRootFormat();
	}
	
	public static JavaLanguageServer getLanguageServer (Consumer <Diagnostic> consumer) throws InterruptedException, ExecutionException {
		final var server = new JavaLanguageServer();
		server.connect(new IDELanguageClient () {

			@Override
			public void telemetryEvent(Object object) {
				
			}

			@Override
			public void publishDiagnostics(PublishDiagnosticsParams diagnostics) {
				diagnostics.getDiagnostics().forEach(consumer);
			}

			@Override
			public void showMessage(MessageParams messageParams) {
				
			}

			@Override
			public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams requestParams) {
				return null;
			}

			@Override
			public void logMessage(MessageParams message) {
				
			}

			@Override
			public void semanticHighlights(SemanticHighlight highlights) {
				
			}
			
			@Override
			public CompletableFuture <Void> registerCapability (RegistrationParams params) {
				return CompletableFuture.completedFuture(null);
			}
		});
		server.initialize(getInitParams()).get();
		server.initialized();
		return server;
	}

	private static InitializeParams getInitParams() {
		
		final var params = new InitializeParams();
		final var root = new WorkspaceFolder();
		
		root.setName("Example project");
		root.setUri(new File("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project").toURI().toString());
		params.setWorkspaceFolders(List.of(root));
		
		return params;
	}
	
	static void shutdown (JavaLanguageServer server) {
		server.shutdown().whenComplete((a, b) -> {
			server.exit();
		});
	}
	
}