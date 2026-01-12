package com.library_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    @org.junit.jupiter.api.Test
    void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result);
    }
    @org.junit.jupiter.api.Test
    void minus(){
        int result = 10 -9;
        assertEquals(1,result);
    }
    @org.junit.jupiter.api.Test
    void mul(){
        int result = 10 * 10;
        assertEquals(100,result);
    }
    @org.junit.jupiter.api.Test
    void divide(){
        int result = 4 / 2;
        assertEquals(2,result);
    }
}
