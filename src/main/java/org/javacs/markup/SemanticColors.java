package org.javacs.markup;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.Range;

public class SemanticColors {
    public URI uri;
    public List<Range>
        packages = new ArrayList<>(),
        enumTypes = new ArrayList<>(),
        classNames = new ArrayList<>(),
        annotationTypes = new ArrayList<>(),
        interfaces = new ArrayList<>(),
        enums = new ArrayList<>(),
        statics = new ArrayList<>(),
        fields = new ArrayList<>(),
        parameters = new ArrayList<>(),
        locals = new ArrayList<>(),
        exceptionParams = new ArrayList<>(),
        methods = new ArrayList<>(),
        constructors = new ArrayList<>(),
        staticInits = new ArrayList<>(),
        instanceInits = new ArrayList<>(),
        typeParams = new ArrayList<>(),
        resourceVariables = new ArrayList<>();
}
