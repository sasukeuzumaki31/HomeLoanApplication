package com.group4.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LandVerificationOfficer extends User{
    @Id
    private int userId;
    private String officeName;
    private String officeContact;

    public LandVerificationOfficer() {
    }

    public LandVerificationOfficer(String officeName, String officeContact) {
        this.officeName = officeName;
        this.officeContact = officeContact;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOfficeContact() {
        return officeContact;
    }

    public void setOfficeContact(String officeContact) {
        this.officeContact = officeContact;
    }
}
