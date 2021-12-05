package com.company.books.backend.examples.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    public static void first() {
        System.out.println("first");
    }

    @AfterAll
    public static void last() {
        System.out.println("last");
    }

    @BeforeEach
    public void objectInstance() {
        System.out.println("BeforeEach");
        calculator = new Calculator();
    }

    @AfterEach
    public void afterTest() {
        System.out.println("AfterEach");
    }

    @Test
    @DisplayName("Test uses assertEqual")
    @Disabled("this test is not executed")
    public void calculatorAssertEqualTest() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(3, calculator.rest(4, 1));
        assertEquals(9, calculator.multply(3, 3));
        assertEquals(2, calculator.divide(4, 2));
        System.out.println("calculatorAssertEqualTest");
    }

    @Test
    public void calculatorTrueFalse() {
        assertTrue(calculator.add(1, 1) == 2);
        assertTrue(calculator.rest(4, 1) == 3);
        assertFalse(calculator.multply(3, 3) == 8);
        assertFalse(calculator.divide(4, 2) == 1);
        System.out.println("calculatorTrueFalse");
    }
}
