package com.group4.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMICalculator {
    private double loanAmount;
    private double rateOfInterest;
    private int tenure;


    public double getEMIAmount() {
        double emi = 0;


        double r = (this.rateOfInterest / 12) / 100;
        double t = (double) this.tenure * 12;
        double temp = (Math.pow(1 + r, t));
        emi = ((this.loanAmount * r) * temp) / (temp - 1);
        return emi;
    }


}
