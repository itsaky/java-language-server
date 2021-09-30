package org.javacs.rewrite;

import java.nio.file.Path;
import java.util.Map;

import org.eclipse.lsp4j.TextEdit;
import org.javacs.CompilerProvider;

public interface Rewrite {
    /** Perform a rewrite across the entire codebase. */
    Map<Path, TextEdit[]> rewrite(CompilerProvider compiler);
    /** CANCELLED signals that the rewrite couldn't be completed. */
    Map<Path, TextEdit[]> CANCELLED = Map.of();

    Rewrite NOT_SUPPORTED = new RewriteNotSupported();
}
