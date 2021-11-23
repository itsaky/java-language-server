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

package org.javacs;

import java.io.File;
import java.nio.file.Paths;
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
		root.setUri(Paths.get("./src/test/projects/maven-project").toUri().toString());
		params.setWorkspaceFolders(List.of(root));
		
		return params;
	}
	
	static void shutdown (JavaLanguageServer server) {
		server.shutdown().whenComplete((a, b) -> {
			server.exit();
		});
	}
	
}