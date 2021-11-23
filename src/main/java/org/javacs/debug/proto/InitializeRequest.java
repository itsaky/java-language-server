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
 * Initialize request; value of command field is 'initialize'. The 'initialize' request is sent as the first request
 * from the client to the debug adapter in order to configure it with client capabilities and to retrieve capabilities
 * from the debug adapter. Until the debug adapter has responded to with an 'initialize' response, the client must not
 * send any additional requests or events to the debug adapter. In addition the debug adapter is not allowed to send any
 * requests or events to the client until it has responded with an 'initialize' response. The 'initialize' request may
 * only be sent once.
 */
public class InitializeRequest extends Request {
    public InitializeRequestArguments arguments;
}
