package com.group4.demo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HomeLoanBorrowingAmountCalculatorTest {
    /**
     * Method under test: {@link HomeLoanBorrowingAmountCalculator#getHomeLoanBorrowingAmount()}
     */
    @Test
    void testGetHomeLoanBorrowingAmount() {
        assertEquals(0.0d,
                (new HomeLoanBorrowingAmountCalculator(10.0d, 10.0d, 1, 10.0d, 10.0d, 10.0d)).getHomeLoanBorrowingAmount());
        assertEquals(Double.NaN, (new HomeLoanBorrowingAmountCalculator(10.0d, 10.0d, 1, Double.NaN, 10.0d, 10.0d))
                .getHomeLoanBorrowingAmount());
    }
}

