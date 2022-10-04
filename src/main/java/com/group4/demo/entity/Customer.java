package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "mobileNumber")})
public class Customer extends User {


    @NotEmpty
    @NotNull
    String customerName;

    String mobileNumber;
    @Email
    String email;


    LocalDate dateOfBirth;

    @NotEmpty
    String gender;

    @NotEmpty
    String nationality;


    String aadharNumber;


    String panNumber;


}
