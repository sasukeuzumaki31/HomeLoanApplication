package com.group4.demo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EMI {
	@Id
	private long EMIId;
	private LocalDate deuDate;
	private double emiAmount;
	private double loanAmount;
	private double interestAmount;
	private long loanAgreementId;

	public EMI() {
		super();
	}

	public EMI(long eMIId, LocalDate deuDate, double emiAmount, double loanAmount, double interestAmount,
			long loanAgreementId) {
		super();
		EMIId = eMIId;
		this.deuDate = deuDate;
		this.emiAmount = emiAmount;
		this.loanAmount = loanAmount;
		this.interestAmount = interestAmount;
		this.loanAgreementId = loanAgreementId;
	}

	public long getEMIId() {
		return EMIId;
	}

	public void setEMIId(long eMIId) {
		EMIId = eMIId;
	}

	public LocalDate getDeuDate() {
		return deuDate;
	}

	public void setDeuDate(LocalDate deuDate) {
		this.deuDate = deuDate;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public long getLoanAgreementId() {
		return loanAgreementId;
	}

	public void setLoanAgreementId(long loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}

}
