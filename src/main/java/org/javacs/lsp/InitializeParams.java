package org.javacs.lsp;

import com.google.gson.JsonElement;
import java.net.URI;
import java.util.*;

public class InitializeParams {
    public int processId;
    public String rootPath;
    public URI rootUri;
    public Set<URI> roots;
    public JsonElement initializationOptions;
    public String trace;
    public List<WorkspaceFolder> workspaceFolders;
}
