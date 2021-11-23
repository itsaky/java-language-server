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

package org.javacs.wan;

/**
 * Doc comment for top level class
 *
 * @author Akash Yadav
 */
public class DocComment {
    
    /**
     * Comment for public field
     */
    public static final int TEST = 0;
    
    /**
     * Comment for private field
     */
    private final int PRIVATE_TEST = 1;
    
    /**
     * Private method {@link #test() }
     * @param i An integer
     * @return Another int
     * @see #test()
     */
    private int test (int i) {
        // Statement comment in private
        int i2 = 0;
        return 0;
    }
    
    /**
     * Public method
     */
    public void test () {
        // Statement comment in public
        int i = 0;
    }
    
    /**
     * ClassPrivate
     */
    private class ClassPrivate {}
    
    // ClassPrivate2
    private class ClassPrivate2 {}
    
    /**
     * ClassPublic
     */
    public class ClassPublic {}
    
    // ClassPublic2
    public class ClassPublic2 {}
}