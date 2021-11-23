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
 * An ExceptionPathSegment represents a segment in a path that is used to match leafs or nodes in a tree of exceptions.
 * If a segment consists of more than one name, it matches the names provided if 'negate' is false or missing or it
 * matches anything except the names provided if 'negate' is true.
 */
public class ExceptionPathSegment {
    /**
     * If false or missing this segment matches the names provided, otherwise it matches anything except the names
     * provided.
     */
    public Boolean negate;
    /** Depending on the value of 'negate' the names that should match or not match. */
    public String[] names;
}
