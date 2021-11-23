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
 * Event message for 'initialized' event type. This event indicates that the debug adapter is ready to accept
 * configuration requests (e.g. SetBreakpointsRequest, SetExceptionBreakpointsRequest). A debug adapter is expected to
 * send this event when it is ready to accept configuration requests (but not before the 'initialize' request has
 * finished). The sequence of events/requests is as follows: - adapters sends 'initialized' event (after the
 * 'initialize' request has returned) - frontend sends zero or more 'setBreakpoints' requests - frontend sends one
 * 'setFunctionBreakpoints' request - frontend sends a 'setExceptionBreakpoints' request if one or more
 * 'exceptionBreakpointFilters' have been defined (or if 'supportsConfigurationDoneRequest' is not defined or false) -
 * frontend sends other future configuration requests - frontend sends one 'configurationDone' request to indicate the
 * end of the configuration.
 */
public class InitializedEvent extends Event {
    // event: 'initialized';
}
