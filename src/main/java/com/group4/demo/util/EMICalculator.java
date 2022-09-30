package com.group4.demo.util;


public class EMICalculator {
    private double loanAmount;
    private double rateOfInterest;
    private int tenure;

    //no-arg Constructor
    public EMICalculator() {

    }

    //constructor using fields
    public EMICalculator(double loanAmount, double rateOfInterest, int tenure) {
        super();
        this.loanAmount = loanAmount;
        this.rateOfInterest = rateOfInterest;
        this.tenure = tenure;
    }

    //getters and setters

    public double getLoanAMount() {
        return loanAmount;
    }

    public void setLoanAMount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }


    //methods

    public double getEMIAmount() {
        double emi = 0;
        System.out.println(this.tenure);

        double r = (this.rateOfInterest / 12) / 100;
        double t =  this.tenure * 12;
        double temp = (Math.pow(1 + r, t));
        emi = ((this.loanAmount * r) * temp) / (temp - 1);
        System.out.println(emi);
        return emi;
    }

    @Override
    public String toString() {
        return "EMICalculator{" +
                "loanAmount=" + loanAmount +
                ", rateOfInterest=" + rateOfInterest +
                ", tenure=" + tenure +
                '}';
    }
}
