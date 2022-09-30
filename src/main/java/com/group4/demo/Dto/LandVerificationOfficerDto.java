package com.group4.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandVerificationOfficerDto {
    private String officeName;
    private String officeContact;
    private int userId;
    private String password;
    private String role;
}
