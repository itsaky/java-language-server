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

/** Represents a single disassembled instruction. */
public class DisassembledInstruction {
    /**
     * The address of the instruction. Treated as a hex value if prefixed with '0x', or as a decimal value otherwise.
     */
    public String address;
    /** Optional raw bytes representing the instruction and its operands, in an implementation-defined format. */
    public String instructionBytes;
    /** Text representing the instruction and its operands, in an implementation-defined format. */
    public String instruction;
    /** Name of the symbol that correponds with the location of this instruction, if any. */
    public String symbol;
    /**
     * Source location that corresponds to this instruction, if any. Should always be set (if available) on the first
     * instruction returned, but can be omitted afterwards if this instruction maps to the same source file as the
     * previous instruction.
     */
    public Source location;
    /** The line within the source location that corresponds to this instruction, if any. */
    public Integer line;
    /** The column within the line that corresponds to this instruction, if any. */
    public Integer column;
    /** The end line of the range that corresponds to this instruction, if any. */
    public Integer endLine;
    /** The end column of the range that corresponds to this instruction, if any. */
    public Integer endColumn;
}
