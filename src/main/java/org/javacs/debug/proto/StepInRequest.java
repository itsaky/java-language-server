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
 * StepIn request; value of command field is 'stepIn'. The request starts the debuggee to step into a function/method if
 * possible. If it cannot step into a target, 'stepIn' behaves like 'next'. The debug adapter first sends the response
 * and then a 'stopped' event (with reason 'step') after the step has completed. If there are multiple function/method
 * calls (or other targets) on the source line, the optional argument 'targetId' can be used to control into which
 * target the 'stepIn' should occur. The list of possible targets for a given source line can be retrieved via the
 * 'stepInTargets' request.
 */
public class StepInRequest extends Request {
    public StepInArguments arguments;
}
