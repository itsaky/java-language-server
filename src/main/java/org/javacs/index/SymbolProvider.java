package org.javacs.index;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.CancelChecker;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.javacs.CompilerProvider;
import org.javacs.ParseTask;

public class SymbolProvider {

    final CompilerProvider compiler;

    public SymbolProvider(CompilerProvider compiler) {
        this.compiler = compiler;
    }

    public List<SymbolInformation> findSymbols(CancelChecker checker, String query, int limit) {
        LOG.info(String.format("Searching for `%s`...", query));
        var result = new ArrayList<SymbolInformation>();
        var checked = 0;
        var parsed = 0;
        for (var file : compiler.search(query)) {
            checked++;
            // Parse the file and check class members for matches
            LOG.info(String.format("...%s contains text matches", file.getFileName()));
            var task = compiler.parse(file);
            checker.checkCanceled();
            var symbols = findSymbolsMatching(checker, task, query);
            checker.checkCanceled();
            parsed++;
            // If we confirm matches, add them to the results
            if (symbols.size() > 0) {
                LOG.info(String.format("...found %d occurrences", symbols.size()));
            }
            result.addAll(symbols);
            // If results are full, stop
            if (result.size() >= limit) break;
        }

        return result;
    }

    public List<Either<SymbolInformation, DocumentSymbol>> documentSymbols(CancelChecker checker, Path file) {
        var task = compiler.parse(file);
        checker.checkCanceled();
        var symbols = findSymbolsMatching(checker, task, "");
        var result = new ArrayList<Either<SymbolInformation, DocumentSymbol>>();
        for(var symbol : symbols) {
        	result.add(Either.forLeft(symbol));
        }
        return result;
    }

    private List<SymbolInformation> findSymbolsMatching(CancelChecker checker, ParseTask task, String query) {
        var found = new ArrayList<SymbolInformation>();
        checker.checkCanceled();
        new FindSymbolsMatching(task, query).scan(task.root, found);
        return found;
    }

    private static final Logger LOG = Logger.getLogger("main");
}
