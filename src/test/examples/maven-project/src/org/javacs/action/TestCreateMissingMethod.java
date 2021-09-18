package org.javacs.action;

class TestCreateMissingMethod {
    void test() {
        var i = foo(1, "two");
        
    }
    
    private static class Test {
    	static int member;
    }
}