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
 * Disconnect request; value of command field is 'disconnect'. The 'disconnect' request is sent from the client to the
 * debug adapter in order to stop debugging. It asks the debug adapter to disconnect from the debuggee and to terminate
 * the debug adapter. If the debuggee has been started with the 'launch' request, the 'disconnect' request terminates
 * the debuggee. If the 'attach' request was used to connect to the debuggee, 'disconnect' does not terminate the
 * debuggee. This behavior can be controlled with the 'terminateDebuggee' argument (if supported by the debug adapter).
 */
public class DisconnectRequest extends Request {
    public DisconnectArguments arguments;
}
