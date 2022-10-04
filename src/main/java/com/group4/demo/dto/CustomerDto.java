package com.group4.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {



    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Length of customer name should be greater than 2")
    String customerName;
    @NotNull(message = "Contact cannot be null")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Mobile number not valid ")
    @Size(min = 10, max = 10, message = "Please enter 10 digit mobile no without country code")
    String mobileNumber;
    @NotNull(message = "email cannot be null")
    @Email(message = "Invalid Email")
    String email;
    @NotNull(message = "DOB cannot be null")
    String dateOfBirth;
    @NotNull(message = "Gender cannot be null")
    String gender;
    @NotNull(message = "Nationality cannot be null")
    String nationality;
    String aadharNumber;
    String panNumber;
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*\\d)"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$", message = "Password Invalid : password should contain atleast one of the " +
            "following:- Uppercase alphabet, Lowercase alpabet, Digit,Special charecter, Minimum length of password should be 8 charecters")
    String password;
}
