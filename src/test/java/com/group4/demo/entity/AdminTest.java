package com.group4.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdminTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Admin#Admin()}
     *   <li>{@link Admin#setAdminContact(String)}
     *   <li>{@link Admin#setAdminName(String)}
     *   <li>{@link Admin#getAdminContact()}
     *   <li>{@link Admin#getAdminName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Admin actualAdmin = new Admin();
        actualAdmin.setAdminContact("Admin Contact");
        actualAdmin.setAdminName("Admin Name");
        assertEquals("Admin Contact", actualAdmin.getAdminContact());
        assertEquals("Admin Name", actualAdmin.getAdminName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Admin#Admin(String, String)}
     *   <li>{@link Admin#setAdminContact(String)}
     *   <li>{@link Admin#setAdminName(String)}
     *   <li>{@link Admin#getAdminContact()}
     *   <li>{@link Admin#getAdminName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Admin actualAdmin = new Admin("Admin Name", "Admin Contact");
        actualAdmin.setAdminContact("Admin Contact");
        actualAdmin.setAdminName("Admin Name");
        assertEquals("Admin Contact", actualAdmin.getAdminContact());
        assertEquals("Admin Name", actualAdmin.getAdminName());
    }
}

