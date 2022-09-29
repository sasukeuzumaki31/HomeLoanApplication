package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LandVerificationOfficer extends User {
    @Id
    private int userId;

    @NotNull
    @NotEmpty
    private String officeName;

    @NotNull
    @NotEmpty
    private String officeContact;


}
