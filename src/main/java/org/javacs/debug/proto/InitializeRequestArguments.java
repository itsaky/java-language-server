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

/** Arguments for 'initialize' request. */
public class InitializeRequestArguments {
    /** The ID of the (frontend) client using this adapter. */
    public String clientID;
    /** The human readable name of the (frontend) client using this adapter. */
    public String clientName;
    /** The ID of the debug adapter. */
    public String adapterID;
    /** The ISO-639 locale of the (frontend) client using this adapter, e.g. en-US or de-CH. */
    public String locale;
    /** If true all line numbers are 1-based (default). */
    public Boolean linesStartAt1;
    /** If true all column numbers are 1-based (default). */
    public Boolean columnsStartAt1;
    /**
     * Determines in what format paths are specified. The default is 'path', which is the native format. Values: 'path',
     * 'uri', etc.
     */
    public String pathFormat;
    /** Client supports the optional type attribute for variables. */
    public Boolean supportsVariableType;
    /** Client supports the paging of variables. */
    public Boolean supportsVariablePaging;
    /** Client supports the runInTerminal request. */
    public Boolean supportsRunInTerminalRequest;
    /** Client supports memory references. */
    public Boolean supportsMemoryReferences;
}
