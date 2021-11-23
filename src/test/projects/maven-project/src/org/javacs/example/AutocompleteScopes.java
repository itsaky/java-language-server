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

public class AutocompleteScopes {
    static void testOuterStaticMethod() { }
    void testOuterMethods() { }

    static class Super {
        static void testInheritedStaticMethod() { }
        void testInheritedMethods() { }
    }

    static class StaticSub extends Super {
        void test(String testArguments) {
            int testLocalVariables;
            t;
            // Locals
            // YES: testLocalVariables, testArguments
            //
            // Static methods in enclosing scopes
            // YES: testStatic
            // YES: testOuterStaticMethod
            //
            // Virtual methods in enclosing scopes
            // NO: testInner
            // YES: test
            // NO: testOuterMethods
            //
            // Inherited static methods
            // YES: testInheritedStaticMethod
            //
            // Inherited virtual methods
            // YES: testInheritedMethods
            //
            // this/super in enclosing scopes
            // YES: this, super
            // YES: StaticSub.this, StaticSub.super
            StaticSub.;
            // NO: AutocompleteScopes.this, AutocompleteScopes.super
            AutocompleteScopes.;
            // NO: Super.this, Super.super
            Super.;

            new Object() {
                void testInner() {
                    t;
                    // Locals
                    // YES: testLocalVariables, testArguments
                    //
                    // Static methods in enclosing scopes
                    // YES: testStatic
                    // YES: testOuterStaticMethod
                    //
                    // Virtual methods in enclosing scopes
                    // YES: testInner
                    // YES: test
                    // NO: testOuterMethods
                    //
                    // Inherited static methods
                    // YES: testInheritedStaticMethod
                    //
                    // Inherited virtual methods
                    // YES: testInheritedMethods
                    //
                    // this/super in enclosing scopes
                    // YES: this, super
                    // YES: StaticSub.this, StaticSub.super
                    StaticSub.;
                    // NO: AutocompleteScopes.this, AutocompleteScopes.super
                    AutocompleteScopes.;
                    // NO: Super.this, Super.super
                    Super.;
                }
            };
        }

        static void testStatic(String testArguments) {
            int testLocalVariables;
            t;
            // Locals
            // YES: testLocalVariables, testArguments
            //
            // Static methods in enclosing scopes
            // YES: testStatic
            // YES: testOuterStaticMethod
            //
            // Virtual methods in enclosing scopes
            // NO: testInner
            // NO: test
            // NO: testOuterMethods
            //
            // Inherited static methods
            // YES: testInheritedStaticMethod
            //
            // Inherited virtual methods
            // NO: testInheritedMethods
            //
            // this/super in enclosing scopes
            // NO: this, super
            // NO: StaticSub.this, StaticSub.super
            StaticSub.;
            // NO: AutocompleteScopes.this, AutocompleteScopes.super
            AutocompleteScopes.;
            // NO: Super.this, Super.super
            Super.;

            new Object() {
                void testInner() {
                    t;
                    // Locals
                    // YES: testLocalVariables, testArguments
                    //
                    // Static methods in enclosing scopes
                    // YES: testStatic
                    // YES: testOuterStaticMethod
                    //
                    // Virtual methods in enclosing scopes
                    // YES: testInner
                    // NO: test
                    // NO: testOuterMethods
                    //
                    // Inherited static methods
                    // YES: testInheritedStaticMethod
                    //
                    // Inherited virtual methods
                    // NO: testInheritedMethods
                    //
                    // this/super in enclosing scopes
                    // YES: this, super
                    // NO: StaticSub.this, StaticSub.super
                    StaticSub.;
                    // NO: AutocompleteScopes.this, AutocompleteScopes.super
                    AutocompleteScopes.;
                    // NO: Super.this, Super.super
                    Super.;
                }
            };
        }
    }

    class Sub extends Super {
        void test(String testArguments) {
            int testLocalVariables;
            t;
            // Locals
            // YES: testLocalVariables, testArguments
            //
            // Methods in enclosing scopes
            // NO: testInner
            // YES: test
            // YES: testOuterMethods, testOuterStaticMethod
            //
            // Inherited methods
            // YES: testInheritedMethods, testInheritedStaticMethod
            //
            // this/super in enclosing scopes
            // YES: this, super
            // YES: Sub.this, Sub.super
            Sub.;
            // YES: AutocompleteScopes.this, AutocompleteScopes.super
            AutocompleteScopes.;
            // NO: Super.this, Super.super
            Super.;

            new Object() {
                void testInner() {
                    t;
                    // Locals
                    // YES: testLocalVariables, testArguments
                    //
                    // Methods in enclosing scopes
                    // YES: testInner
                    // YES: test
                    // YES: testOuterMethods, testOuterStaticMethod
                    //
                    // Inherited methods
                    // YES: testInheritedMethods, testInheritedStaticMethod
                    //
                    // this/super in enclosing scopes
                    // YES: this, super
                    // YES: Sub.this, Sub.super
                    Sub.;
                    // YES: AutocompleteScopes.this, AutocompleteScopes.super
                    AutocompleteScopes.;
                    // NO: Super.this, Super.super
                    Super.;
                }
            };
        }
    }

    void testBlock(String testArg) {
        int testMethodLocal = 1;

        {
            int testBlockLocal = 2;
            t;
        }
    }
}