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

public class StoppedEventBody {
    /**
     * The reason for the event. For backward compatibility this string is shown in the UI if the 'description'
     * attribute is missing (but it must not be translated). Values: 'step', 'breakpoint', 'exception', 'pause',
     * 'entry', 'goto', 'function breakpoint', 'data breakpoint', etc.
     */
    public String reason;
    /**
     * The full reason for the event, e.g. 'Paused on exception'. This string is shown in the UI as is and must be
     * translated.
     */
    public String description;
    /** The thread which was stopped. */
    public Long threadId;
    /** A value of true hints to the frontend that this event should not change the focus. */
    public Boolean preserveFocusHint;
    /**
     * Additional information. E.g. if reason is 'exception', text contains the exception name. This string is shown in
     * the UI.
     */
    public String text;
    /**
     * If 'allThreadsStopped' is true, a debug adapter can announce that all threads have stopped. - The client should
     * use this information to enable that all threads can be expanded to access their stacktraces. - If the attribute
     * is missing or false, only the thread with the given threadId can be expanded.
     */
    public Boolean allThreadsStopped;
}
