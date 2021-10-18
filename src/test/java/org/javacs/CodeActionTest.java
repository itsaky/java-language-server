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
	private static JavaLanguageServer server ;
	
	static {
		try {
			server = LanguageServerProvider.getLanguageServer(CodeActionTest::addDiagnostic);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
	
	public CodeActionTest() {}
	
	public static void addDiagnostic (Diagnostic d ) {
		diagnostics.add(d);
	}
	
	@Test
	public void testImplementAbstractMethods() throws InterruptedException, ExecutionException {
		assertTrue(quickFix("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/action/TestImplementAbstractMethods.java").contains("Implement abstract methods"));
	}
	
	@Test
	public void testImplementAbstractMethodsAnonymous() throws InterruptedException, ExecutionException {
		assertTrue(quickFix("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/action/TestImplementAbstractMethodsAnonymous.java").contains("Implement abstract methods"));
	}
	
	static List<String> quickFix (String file) throws InterruptedException, ExecutionException {
		
		// Clear any diagnostics from previous files
		diagnostics.clear();
		
		var path = Paths.get(file);
		server.lint (List.of(path));
		
		var params = new CodeActionParams();
		params.setContext(new CodeActionContext(diagnostics));
		params.setTextDocument(new TextDocumentIdentifier(path.toUri().toString()));
		
		var future = server.codeAction(params);
		var list = new ArrayList<>();
		try {
			list = (ArrayList) future.get();
		} catch (Throwable th) {
			th.printStackTrace();
		}
		
		return mapTitles(logAndReturn(list));
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