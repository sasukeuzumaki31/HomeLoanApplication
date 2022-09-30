package com.group4.demo.util;

public class HomeLoanBorrowingAmountCalculator {
	
	private double loanAmount;
	private double rateOfInterest;
	private int tenure;
	private double totalAnnualIncome;
	private double monthlyExpenses;
	private double otherMonthlyExpenses;
	
	
	//no-arg contructor
	public HomeLoanBorrowingAmountCalculator() {

	}
	
	//constructor using field
	public HomeLoanBorrowingAmountCalculator(double loanAmount, double rateOfInterest, int tenure,
			double totalAnnualIncome, double monthlyExpenses, double otherMonthlyExpenses) {
		super();
		this.loanAmount = loanAmount;
		this.rateOfInterest = rateOfInterest;
		this.tenure = tenure;
		this.totalAnnualIncome = totalAnnualIncome;
		this.monthlyExpenses = monthlyExpenses;
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}

	//getters and setters
	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
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

	public double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}

	public void setTotalAnnualIncome(double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}

	public double getMonthlyExpenses() {
		return monthlyExpenses;
	}

	public void setMonthlyExpenses(double monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}

	public double getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}

	public void setOtherMonthlyExpenses(double otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}
	
	
	//function
	public double getHomeLoanBorrowingAmount() {
		double monthlyIncomme = this.totalAnnualIncome/12;
		if(monthlyIncomme < 15000.0) {
			return 0.0;
		}
		double annualSavings = (this.totalAnnualIncome - (12*(this.monthlyExpenses+this.otherMonthlyExpenses)));
		double eligibleAmount = annualSavings*(this.tenure) + ((this.rateOfInterest*annualSavings)/100);
		if(eligibleAmount > this.loanAmount){
			return this.loanAmount;
		}
		return eligibleAmount;
	}

}
