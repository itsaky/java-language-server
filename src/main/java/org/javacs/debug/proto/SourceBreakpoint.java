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

/** Properties of a breakpoint or logpoint passed to the setBreakpoints request. */
public class SourceBreakpoint {
    /** The source line of the breakpoint or logpoint. */
    public int line;
    /** An optional source column of the breakpoint. */
    public Integer column;
    /** An optional expression for conditional breakpoints. */
    public String condition;
    /**
     * An optional expression that controls how many hits of the breakpoint are ignored. The backend is expected to
     * interpret the expression as needed.
     */
    public String hitCondition;
    /**
     * If this attribute exists and is non-empty, the backend must not 'break' (stop) but log the message instead.
     * Expressions within {} are interpolated.
     */
    public String logMessage;
}
