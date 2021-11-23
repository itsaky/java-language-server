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
 * Event message for 'capabilities' event type. The event indicates that one or more capabilities have changed. Since
 * the capabilities are dependent on the frontend and its UI, it might not be possible to change that at random times
 * (or too late). Consequently this event has a hint characteristic: a frontend can only be expected to make a 'best
 * effort' in honouring individual capabilities but there are no guarantees. Only changed capabilities need to be
 * included, all other capabilities keep their values.
 */
public class CapabilitiesEvent extends Event {
    // event: 'capabilities';
    public CapabilitiesEventBody body;
}
