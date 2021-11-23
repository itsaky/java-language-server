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

package org.javacs.debug.proto;

/** A Scope is a named container for variables. Optionally a scope can map to a source or a range within a source. */
public class Scope {
    /**
     * Name of the scope such as 'Arguments', 'Locals', or 'Registers'. This string is shown in the UI as is and can be
     * translated.
     */
    public String name;
    /**
     * An optional hint for how to present this scope in the UI. If this attribute is missing, the scope is shown with a
     * generic UI. Values: 'arguments': Scope contains method arguments. 'locals': Scope contains local variables.
     * 'registers': Scope contains registers. Only a single 'registers' scope should be returned from a 'scopes'
     * request. etc.
     */
    public String presentationHint;
    /**
     * The variables of this scope can be retrieved by passing the value of variablesReference to the VariablesRequest.
     */
    public long variablesReference;
    /**
     * The number of named variables in this scope. The client can use this optional information to present the
     * variables in a paged UI and fetch them in chunks.
     */
    public Integer namedVariables;
    /**
     * The number of indexed variables in this scope. The client can use this optional information to present the
     * variables in a paged UI and fetch them in chunks.
     */
    public Integer indexedVariables;
    /** If true, the number of variables in this scope is large or expensive to retrieve. */
    public boolean expensive;
    /** Optional source for this scope. */
    public Source source;
    /** Optional start line of the range covered by this scope. */
    public Integer line;
    /** Optional start column of the range covered by this scope. */
    public Integer column;
    /** Optional end line of the range covered by this scope. */
    public Integer endLine;
    /** Optional end column of the range covered by this scope. */
    public Integer endColumn;
}
