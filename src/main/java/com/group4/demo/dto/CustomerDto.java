package com.group4.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {



    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Length of customer name should be greater than 2")
    String customerName;
    @NotNull(message = "Contact cannot be null")
    @Pattern(regexp = "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$", message = "Mobile number not valid ")
    @Size(min = 10, max = 10, message = "Please enter 10 digit mobile no without country code")
    String mobileNumber;
    @NotNull(message = "email cannot be null")
    @Email(message = "Invalid Email")
    String email;
    @NotNull(message = "DOB cannot be null")
    @NotEmpty(message = "DOB cannot be empty")
    String dateOfBirth;
    @NotNull(message = "Gender cannot be null")
    @NotEmpty(message = "Gender cannot be empty")
    String gender;
    @NotNull(message = "Nationality cannot be null")
    @NotEmpty(message = "Nationality cannot be empty")
    String nationality;
    @Pattern(regexp = "^[2-9]\\d{11}$", message = "Invalid Aadhaar number")
    String aadharNumber;
    @Pattern(regexp = "[A-Z]{5}\\d{4}[A-Z]{1}", message = "Invalid pan number")
    String panNumber;
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*\\d)"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$", message = "Password Invalid : password should contain atleast one of the " +
            "following:- Uppercase alphabet, Lowercase alpabet, Digit,Special charecter, Minimum length of password should be 8 charecters")
    String password;
}