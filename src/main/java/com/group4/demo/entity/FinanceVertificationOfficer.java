package com.group4.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FinanceVertificationOfficer extends  User{
    @Id
    private int userId;

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String finOfficerName;
    private String finOfficerContact;

    public FinanceVertificationOfficer() {
    }

    public FinanceVertificationOfficer(String finOfficerName, String finOfficerContact) {
        this.finOfficerName = finOfficerName;
        this.finOfficerContact = finOfficerContact;
    }

    public String getFinOfficerName() {
        return finOfficerName;
    }

    public void setFinOfficerName(String finOfficerName) {
        this.finOfficerName = finOfficerName;
    }

    public String getFinOfficerContact() {
        return finOfficerContact;
    }

    public void setFinOfficerContact(String finOfficerContact) {
        this.finOfficerContact = finOfficerContact;
    }
}
