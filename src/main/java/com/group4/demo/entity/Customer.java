package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends User {


    @NotEmpty
    @NotNull
    String customerName;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    String mobileNumber;
    @Email
    String email;


    LocalDate dateOfBirth;

    @NotEmpty
    String gender;

    @NotEmpty
    String nationality;

    @Pattern(regexp = "^[2-9][0-9]{11}$")
    String aadharNumber;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
    String panNumber;


}
