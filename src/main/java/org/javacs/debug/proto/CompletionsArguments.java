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

/** Arguments for 'completions' request. */
public class CompletionsArguments {
    /**
     * Returns completions in the scope of this stack frame. If not specified, the completions are returned for the
     * global scope.
     */
    public Integer frameId;
    /**
     * One or more source lines. Typically this is the text a user has typed into the debug console before he asked for
     * completion.
     */
    public String text;
    /** The character position for which to determine the completion proposals. */
    public int column;
    /**
     * An optional line for which to determine the completion proposals. If missing the first line of the text is
     * assumed.
     */
    public Integer line;
}
