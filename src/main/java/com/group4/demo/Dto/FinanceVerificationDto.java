package com.group4.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceVerificationDto {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2,message = "Length of finance officer name should be greater than 2")
    private String finOfficerName;
    @NotNull(message = "Contact cannot be null")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",message = "Mobile number not valid ")
    @Size(min = 10,max = 10,message = "Please enter 10 digit mobile no without country code")
    private String finOfficerContact;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$",message = "Password Invalid : password should contain atleast one of the " +
            "following:- Uppercase alphabet, Lowercase alpabet, Digit,Special charecter, Minimum length of password should be 8 charecters")
    private String password;
    private String role;
}
