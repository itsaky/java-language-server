package org.javacs;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileFinder {
	
	public static Path path (String resource) {
		return Paths.get("./src/test/projects/maven-project/src/" + resource);
	}
	
}