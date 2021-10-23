package org.javacs;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

public class DiagnosticsTest extends BaseTest {
	
	@Test
	public void testNotUsed () throws InterruptedException, ExecutionException {
		var list = lint("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/warn/Unused.java");
		assertNotNull(list);
		assertTrue(list.contains("unused_throws"));
		assertTrue(list.contains("unused_local"));
		assertTrue(list.contains("unused_param"));
		assertTrue(list.contains("unused_field"));
		assertTrue(list.contains("unused_method"));
		assertTrue(list.contains("unused_class"));
		assertTrue(list.contains("unused_other"));
	}
	
	@Test
	public void testNotThrown () throws InterruptedException, ExecutionException {
		var list = lint("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/warn/NotThrown.java");
		assertNotNull(list);
		assertTrue(list.contains("unused_throws"));
	}
	
	@Test
	public void testSemantics () throws InterruptedException, ExecutionException {
		var list = lint("/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/warn/DocComment.java");
		assertNotNull(list);
	}
	
	@Test
	public void testContinuousLints () throws InterruptedException, ExecutionException {
		final String path = "/storage/emulated/0/AppProjects/java-language-server/src/test/projects/maven-project/src/org/javacs/warn/DocComment.java";
		
		LOG.info ("Test continuous lint calls");
		// Call lint in less than a second
		lint(path);
		lint(path);
		lint(path);
	}
	
	private List<String> lint (String path) throws InterruptedException, ExecutionException {
		final var list = new ArrayList<String>();
		final var server = LanguageServerProvider.getLanguageServer(diagnostic -> {
			list.add(diagnostic.getCode().getLeft());
		});
		server.lint(List.of(Paths.get(path))).get();
		LanguageServerProvider.shutdown(server);
		return list;
		
	}
}