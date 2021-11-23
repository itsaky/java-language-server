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

public class DataBreakpointInfoResponseBody {
    /**
     * An identifier for the data on which a data breakpoint can be registered with the setDataBreakpoints request or
     * null if no data breakpoint is available.
     */
    public String dataId;
    /** UI string that describes on what data the breakpoint is set on or why a data breakpoint is not available. */
    public String description;
    /**
     * Optional attribute listing the available access types for a potential data breakpoint. A UI frontend could
     * surface this information. 'read' | 'write' | 'readWrite'.
     */
    public String[] accessTypes;
    /** Optional attribute indicating that a potential data breakpoint could be persisted across sessions. */
    public Boolean canPersist;
}
