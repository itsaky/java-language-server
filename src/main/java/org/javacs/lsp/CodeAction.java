package org.javacs.lsp;

import java.util.List;

public class CodeAction {
    public String title, kind;
    public String diagnosticMessage;
    public List<Diagnostic> diagnostics = new java.util.ArrayList<>();
    public WorkspaceEdit edit;
    public Command command;
    public static CodeAction NONE;
}
