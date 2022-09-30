package com.group4.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceVerificationDto {

    private String finOfficerName;
    private String finOfficerContact;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String password;
    private String role;
}
