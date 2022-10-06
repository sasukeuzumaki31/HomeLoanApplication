package com.group4.demo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EMICalculatorTest {

    @Test
    void testGetEMIAmount() {
        assertEquals(0.88d, (new EMICalculator(10.0d, 10.0d, 1)).getEMIAmount());
    }
}

