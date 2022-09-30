package com.group4.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {


   @NotNull
    @NotEmpty
    private String adminName;

    @NotNull
    private String adminContact;
}
