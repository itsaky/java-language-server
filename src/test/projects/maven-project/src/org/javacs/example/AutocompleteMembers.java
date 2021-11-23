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

package org.javacs.example;

public class AutocompleteMembers {
    private String testFields;
    private static String testFieldStatic;

    {
        t; // testFields, testFieldStatic, testMethods, testMethodStatic
        this.t; // testFields, testMethods
        AutocompleteMembers.t; // testFieldStatic, testMethodStatic
        this::t; // testMethods
        AutocompleteMembers::t; // testMethods, testMethodStatic
    }

    static {
        t; // testFieldStatic
        AutocompleteMembers.t; // testFieldStatic
        AutocompleteMembers::t; // testMethods, testMethodStatic
    }

    private void testMethods(String testArguments) {
        t; // testFields, testFieldStatic, testMethods, testMethodStatic, testArguments
        this.t; // testFields, testMethods
        AutocompleteMembers.t; // testFieldStatic, testMethodStatic
        java.util.function.Supplier<String> test = this::t; // testMethods
        java.util.function.Supplier<String> test = AutocompleteMembers::t; // testMethods, testMethodStatic
    }

    private static void testMethodStatic(String testArguments) {
        t; // testFieldStatic, testArguments
        AutocompleteMembers.t; // testFieldStatic
        AutocompleteMembers::t; // testMethods, testMethodStatic
    }
}