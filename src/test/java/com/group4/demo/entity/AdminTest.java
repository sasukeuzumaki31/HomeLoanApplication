package com.group4.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdminTest {

    @Test
    void testConstructor() {
        Admin actualAdmin = new Admin();
        actualAdmin.setAdminContact("1234567891");
        actualAdmin.setAdminName("Rajesh");
        assertEquals("1234567891", actualAdmin.getAdminContact());
        assertEquals("Rajesh", actualAdmin.getAdminName());
    }

    @Test
    void testConstructor2() {
        Admin actualAdmin = new Admin("Rajesh", "1234567891");
        actualAdmin.setAdminContact("1234567891");
        actualAdmin.setAdminName("Rajesh");
        assertEquals("1234567891", actualAdmin.getAdminContact());
        assertEquals("Rajesh", actualAdmin.getAdminName());
    }
}

