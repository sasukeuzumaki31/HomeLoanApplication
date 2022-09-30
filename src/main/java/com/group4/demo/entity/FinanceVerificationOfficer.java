package com.group4.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
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
