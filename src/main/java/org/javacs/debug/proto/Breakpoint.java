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

/** Information about a Breakpoint created in setBreakpoints or setFunctionBreakpoints. */
public class Breakpoint {
    /**
     * An optional identifier for the breakpoint. It is needed if breakpoint events are used to update or remove
     * breakpoints.
     */
    public Integer id;
    /** If true breakpoint could be set (but not necessarily at the desired location). */
    public boolean verified;
    /**
     * An optional message about the state of the breakpoint. This is shown to the user and can be used to explain why a
     * breakpoint could not be verified.
     */
    public String message;
    /** The source where the breakpoint is located. */
    public Source source;
    /** The start line of the actual range covered by the breakpoint. */
    public Integer line;
    /** An optional start column of the actual range covered by the breakpoint. */
    public Integer column;
    /** An optional end line of the actual range covered by the breakpoint. */
    public Integer endLine;
    /**
     * An optional end column of the actual range covered by the breakpoint. If no end line is given, then the end
     * column is assumed to be in the start line.
     */
    public Integer endColumn;
}
