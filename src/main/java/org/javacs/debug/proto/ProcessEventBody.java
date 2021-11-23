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

public class ProcessEventBody {
    /**
     * The logical name of the process. This is usually the full path to process's executable file. Example:
     * /home/example/myproj/program.js.
     */
    public String name;
    /** The system process id of the debugged process. This property will be missing for non-system processes. */
    public Integer systemProcessId;
    /** If true, the process is running on the same computer as the debug adapter. */
    public Boolean isLocalProcess;
    /**
     * Describes how the debug engine started debugging this process. 'launch': Process was launched under the debugger.
     * 'attach': Debugger attached to an existing process. 'attachForSuspendedLaunch': A project launcher component has
     * launched a new process in a suspended state and then asked the debugger to attach.
     */
    public String startMethod;
    /**
     * The size of a pointer or address for this process, in bits. This value may be used by clients when formatting
     * addresses for display.
     */
    public Integer pointerSize;
}
