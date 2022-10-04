package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FinanceVerificationOfficer extends User {


    @NotNull
    @NotEmpty
    private String finOfficerName;

    @NotNull
    @NotEmpty
    private String finOfficerContact;


}
