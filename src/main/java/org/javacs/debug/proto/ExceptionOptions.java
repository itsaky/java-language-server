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

/** An ExceptionOptions assigns configuration options to a set of exceptions. */
public class ExceptionOptions {
    /**
     * A path that selects a single or multiple exceptions in a tree. If 'path' is missing, the whole tree is selected.
     * By convention the first segment of the path is a category that is used to group exceptions in the UI.
     */
    public ExceptionPathSegment[] path;
    /**
     * Condition when a thrown exception should result in a break. never: never breaks, always: always breaks,
     * unhandled: breaks when excpetion unhandled, userUnhandled: breaks if the exception is not handled by user code.
     */
    public String breakMode;
}
