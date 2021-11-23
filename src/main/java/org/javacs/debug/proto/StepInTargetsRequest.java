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
 * StepInTargets request; value of command field is 'stepInTargets'. This request retrieves the possible stepIn targets
 * for the specified stack frame. These targets can be used in the 'stepIn' request. The StepInTargets may only be
 * called if the 'supportsStepInTargetsRequest' capability exists and is true.
 */
public class StepInTargetsRequest extends Request {
    public StepInTargetsArguments arguments;
}
