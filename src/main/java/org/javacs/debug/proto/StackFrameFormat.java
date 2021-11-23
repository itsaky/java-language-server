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

/** Provides formatting information for a stack frame. */
public class StackFrameFormat extends ValueFormat {
    /** Displays parameters for the stack frame. */
    public Boolean parameters;
    /** Displays the types of parameters for the stack frame. */
    public Boolean parameterTypes;
    /** Displays the names of parameters for the stack frame. */
    public Boolean parameterNames;
    /** Displays the values of parameters for the stack frame. */
    public Boolean parameterValues;
    /** Displays the line number of the stack frame. */
    public Boolean line;
    /** Displays the module of the stack frame. */
    public Boolean module;
    /** Includes all stack frames, including those the debug adapter might otherwise hide. */
    public Boolean includeAll;
}
