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

/** Detailed information about an exception that has occurred. */
public class ExceptionDetails {
    /** Message contained in the exception. */
    public String message;
    /** Short type name of the exception object. */
    public String typeName;
    /** Fully-qualified type name of the exception object. */
    public String fullTypeName;
    /** Optional expression that can be evaluated in the current scope to obtain the exception object. */
    public String evaluateName;
    /** Stack trace at the time the exception was thrown. */
    public String stackTrace;
    /** Details of the exception contained by this exception, if any. */
    public ExceptionDetails[] innerException;
}
