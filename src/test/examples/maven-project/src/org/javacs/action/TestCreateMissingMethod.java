package org.javacs.action;

class TestCreateMissingMethod {
    void test() {
        TestAddImport addImport;
        addImport.tester.test.foo();
    }
    
    TestCreateMissingMethod get() { return this; }
}