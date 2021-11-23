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

public class ReadMemoryResponseBody {
    /**
     * The address of the first byte of data returned. Treated as a hex value if prefixed with '0x', or as a decimal
     * value otherwise.
     */
    public String address;
    /**
     * The number of unreadable bytes encountered after the last successfully read byte. This can be used to determine
     * the number of bytes that must be skipped before a subsequent 'readMemory' request will succeed.
     */
    public Integer unreadableBytes;
    /** The bytes read from memory, encoded using base64. */
    public String data;
}
