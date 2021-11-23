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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.eclipse.lsp4j.CodeAction;
import org.eclipse.lsp4j.CodeActionContext;
import org.eclipse.lsp4j.CodeActionParams;
import org.eclipse.lsp4j.Command;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.javacs.services.JavaLanguageServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeActionTest extends BaseTest {
	
	private static final List<Diagnostic> diagnostics = new ArrayList<>();
	
	public CodeActionTest() {}
	
	public static void addDiagnostic (Diagnostic d ) {
		diagnostics.add(d);
	}
	
	@Test
	public void testImplementAbstractMethods() throws InterruptedException, ExecutionException {
		assertTrue(quickFix("org/javacs/action/TestImplementAbstractMethods.java").contains("Implement abstract methods"));
	}
	
	@Test
	public void testImplementAbstractMethodsAnonymous() throws InterruptedException, ExecutionException {
		assertTrue(quickFix("org/javacs/action/TestImplementAbstractMethodsAnonymous.java").contains("Implement abstract methods"));
	}
	
	static List<String> quickFix (String file) throws InterruptedException, ExecutionException {
		
		// Clear any diagnostics from previous files
		diagnostics.clear();
		
		var server = LanguageServerProvider.getLanguageServer(d -> {
			diagnostics.add (d);
		});
		
		var path = FileFinder.path(file);
		server.lint (List.of(path)).get();
		
		var params = new CodeActionParams();
		params.setContext(new CodeActionContext(diagnostics));
		params.setTextDocument(new TextDocumentIdentifier(path.toUri().toString()));
		
		var future = server.codeAction(params);
		List<Either<Command, CodeAction>> list = new ArrayList<>();
		
		try {
			list = future.get();
		} catch (Throwable th) {
			th.printStackTrace();
		}
		
		LanguageServerProvider.shutdown(server);
		
		return mapTitles(list);
	}
	
	static List<String> mapTitles (List<Either<Command, CodeAction>> actions) {
		return actions.stream()
			.filter(a -> !Objects.isNull(a))
			.map(e -> e.isLeft() ? e.getLeft().getTitle() : e.getRight().getTitle())
			.collect(Collectors.toList());
	}
	
	static List logAndReturn (List list) {
		LOG.info("LOG LIST: " + list);
		return list;
	}
	
}