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

import com.google.gson.JsonObject;

public class OutputEventBody {
    /**
     * The output category. If not specified, 'console' is assumed. Values: 'console', 'stdout', 'stderr', 'telemetry',
     * etc.
     */
    public String category;
    /** The output to report. */
    public String output;
    /**
     * If an attribute 'variablesReference' exists and its value is > 0, the output contains objects which can be
     * retrieved by passing 'variablesReference' to the 'variables' request.
     */
    public Integer variablesReference;
    /** An optional source location where the output was produced. */
    public Source source;
    /** An optional source location line where the output was produced. */
    public Integer line;
    /** An optional source location column where the output was produced. */
    public Integer column;
    /**
     * Optional data to report. For the 'telemetry' category the data will be sent to telemetry, for the other
     * categories the data is shown in JSON format.
     */
    public JsonObject data;
}
