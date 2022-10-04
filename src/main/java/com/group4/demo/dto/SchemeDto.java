package com.group4.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchemeDto {
    private int schemeId;
    @NotNull(message = "Interest Rate cannot be null")
    @Min(value = 3,message ="Interest rate cannot be less than 3")
    private double interestRate;
    @NotNull(message = "Tenure cannot be null")
    @Min(value = 1,message ="Tenure cannot be less than 1")
    private int tenure;
}
