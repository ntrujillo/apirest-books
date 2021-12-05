package com.company.books.backend.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTrueFalseTeory {
    @Test
    public void test1() {
        assertTrue(true);
    }

    @Test
    public void test2() {
        boolean expression = 4 == 4;
        boolean expressionFalse = 4==3;
        assertTrue(expression);
        assertFalse(expressionFalse);
    }
}
