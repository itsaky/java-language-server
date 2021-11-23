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
 * A ColumnDescriptor specifies what module attribute to show in a column of the ModulesView, how to format it, and what
 * the column's label should be. It is only used if the underlying UI actually supports this level of customization.
 */
public class ColumnDescriptor {
    /** Name of the attribute rendered in this column. */
    public String attributeName;
    /** Header UI label of column. */
    public String label;
    /** Format to use for the rendered values in this column. TBD how the format strings looks like. */
    public String format;
    /**
     * Datatype of values in this column. Defaults to 'string' if not specified. 'string' | 'number' | 'boolean' |
     * 'unixTimestampUTC'.
     */
    public String type;
    /** Width of this column in characters (hint only). */
    public Integer width;
}
