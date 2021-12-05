package com.company.books.backend.examples.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssertArrayEquals {
    @Test
    public void testArrayEquals() {
        String[] arr1 = {"a", "b"};
        String[] arr2 = {"a", "b"};
        String[] arr3 = {"a", "b", "c"};

        assertArrayEquals(arr1,arr2);
    }
}
