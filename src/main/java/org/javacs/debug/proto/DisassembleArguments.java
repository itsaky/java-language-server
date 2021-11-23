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

/** Arguments for 'disassemble' request. */
public class DisassembleArguments {
    /** Memory reference to the base location containing the instructions to disassemble. */
    public String memoryReference;
    /** Optional offset (in bytes) to be applied to the reference location before disassembling. Can be negative. */
    public Integer offset;
    /**
     * Optional offset (in instructions) to be applied after the byte offset (if any) before disassembling. Can be
     * negative.
     */
    public Integer instructionOffset;
    /**
     * Number of instructions to disassemble starting at the specified location and offset. An adapter must return
     * exactly this number of instructions - any unavailable instructions should be replaced with an
     * implementation-defined 'invalid instruction' value.
     */
    public int instructionCount;
    /** If true, the adapter should attempt to resolve memory addresses and other values to symbolic names. */
    public Boolean resolveSymbols;
}
