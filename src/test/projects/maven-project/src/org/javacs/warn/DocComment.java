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