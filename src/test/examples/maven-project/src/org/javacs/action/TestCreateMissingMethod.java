package org.javacs.action;

class TestCreateMissingMethod {
    void test() {
        TestAddImport addImport;
        addImport.tester.test.foo("", (char) 0, (byte) 0, false);
    }
    
    TestCreateMissingMethod get() { return this; }
}