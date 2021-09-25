package org.javacs.action;

class TestCreateMissingMethod {
    void test() {
        TestAddImport.tester.foo();
    }
    
    TestCreateMissingMethod get() { return this; }
}