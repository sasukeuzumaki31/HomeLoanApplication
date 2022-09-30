package com.group4.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    String customerName;
    String mobileNumber;
    String email;
    String dateOfBirth;
    String gender;
    String nationality;
    String aadharNumber;
    String panNumber;
    String password;
}
