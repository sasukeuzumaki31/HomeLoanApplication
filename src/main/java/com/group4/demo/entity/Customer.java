package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends User{
    @Id
    private int userId;
    String customerName;
    String mobileNumber;
    String email;
    LocalDate dateOfBirth;
    String gender;
    String nationality;
    String aadharNumber;
    String panNumber;


}
