package com.group4.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LandVerificationOfficer extends User {


    @NotNull
    @NotEmpty
    private String officeName;

    @NotNull
    @NotEmpty
    private String officeContact;


}
