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

public class TerminatedEventBody {
    /**
     * A debug adapter may set 'restart' to true (or to an arbitrary object) to request that the front end restarts the
     * session. The value is not interpreted by the client and passed unmodified as an attribute '__restart' to the
     * 'launch' and 'attach' requests.
     */
    public JsonObject restart;
}
