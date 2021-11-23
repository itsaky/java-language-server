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

import java.util.Map;

/** A structured message object. Used to return errors from requests. */
public class Message {
    /** Unique identifier for the message. */
    public int id;
    /**
     * A format string for the message. Embedded variables have the form '{name}'. If variable name starts with an
     * underscore character, the variable does not contain user data (PII) and can be safely used for telemetry
     * purposes.
     */
    public String format;
    /** An object used as a dictionary for looking up the variables in the format string. */
    Map<String, String> variables;
    /** If true send to telemetry. */
    public Boolean sendTelemetry;
    /** If true show user. */
    public Boolean showUser;
    /** An optional url where additional information about this message can be found. */
    public String url;
    /** An optional label that is presented to the user as the UI for opening the url. */
    public String urlLabel;
}
