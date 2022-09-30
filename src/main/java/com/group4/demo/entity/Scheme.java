package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scheme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int schemeId;
    @NotNull
    private double interestRate;
    @NotNull
    private int tenure;
}
