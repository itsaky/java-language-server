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

/**
 * A GotoTarget describes a code location that can be used as a target in the 'goto' request. The possible goto targets
 * can be determined via the 'gotoTargets' request.
 */
public class GotoTarget {
    /** Unique identifier for a goto target. This is used in the goto request. */
    public int id;
    /** The name of the goto target (shown in the UI). */
    public String label;
    /** The line of the goto target. */
    public int line;
    /** An optional column of the goto target. */
    public Integer column;
    /** An optional end line of the range covered by the goto target. */
    public Integer endLine;
    /** An optional end column of the range covered by the goto target. */
    public Integer endColumn;
    /** Optional memory reference for the instruction pointer value represented by this target. */
    public String instructionPointerReference;
}
