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

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

public class DiagnosticsTest extends BaseTest {
	
	@Test
	public void testNotUsed () throws InterruptedException, ExecutionException {
		var list = lint("org/javacs/warn/Unused.java");
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
		var list = lint("org/javacs/warn/NotThrown.java");
		assertNotNull(list);
		assertTrue(list.contains("unused_throws"));
	}
	
	@Test
	public void testSemantics () throws InterruptedException, ExecutionException {
		var list = lint("org/javacs/warn/DocComment.java");
		assertNotNull(list);
	}
	
	@Test
	public void testContinuousLints () throws InterruptedException, ExecutionException {
		final String path = "org/javacs/warn/DocComment.java";
		
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
		server.lint(List.of(FileFinder.path(path))).get();
		LanguageServerProvider.shutdown(server);
		return list;
		
	}
}