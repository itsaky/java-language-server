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

/** Arguments for 'setExpression' request. */
public class SetExpressionArguments {
    /** The l-value expression to assign to. */
    public String expression;
    /** The value expression to assign to the l-value expression. */
    public String value;
    /**
     * Evaluate the expressions in the scope of this stack frame. If not specified, the expressions are evaluated in the
     * global scope.
     */
    public Integer frameId;
    /** Specifies how the resulting value should be formatted. */
    public ValueFormat format;
}
