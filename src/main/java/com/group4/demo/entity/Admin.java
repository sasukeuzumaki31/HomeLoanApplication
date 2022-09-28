package com.group4.demo.entity;

import javax.persistence.*;

@Entity
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  userId;

    private String adminName;
    private String adminContact;





    public Admin(String adminName, String adminContact) {
        this.adminName = adminName;
        this.adminContact = adminContact;

    }

    public Admin() {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }
}
