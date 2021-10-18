package org.javacs.lens;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.CodeLens;
import org.javacs.ParseTask;

public class CodeLensProvider {

    public static List<CodeLens> find(ParseTask task) {
        var list = new ArrayList<CodeLens>();
        new FindCodeLenses(task.task).scan(task.root, list);
        return list;
    }
}
