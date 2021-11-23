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

public interface DebugServer {
    Capabilities initialize(InitializeRequestArguments req);

    SetBreakpointsResponseBody setBreakpoints(SetBreakpointsArguments req);

    SetFunctionBreakpointsResponseBody setFunctionBreakpoints(SetFunctionBreakpointsArguments req);

    void setExceptionBreakpoints(SetExceptionBreakpointsArguments req);

    void configurationDone();

    void launch(LaunchRequestArguments req);

    void attach(AttachRequestArguments req);

    void disconnect(DisconnectArguments req);

    void terminate(TerminateArguments req);

    void continue_(ContinueArguments req);

    void next(NextArguments req);

    void stepIn(StepInArguments req);

    void stepOut(StepOutArguments req);

    ThreadsResponseBody threads();

    StackTraceResponseBody stackTrace(StackTraceArguments req);

    ScopesResponseBody scopes(ScopesArguments req);

    VariablesResponseBody variables(VariablesArguments req);

    EvaluateResponseBody evaluate(EvaluateArguments req);
}
