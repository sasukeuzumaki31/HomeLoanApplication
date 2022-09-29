package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EMI {
	@Id
	private long EMIId;
	private LocalDate deuDate;
	private double emiAmount;
	private double loanAmount;
	private double interestAmount;
	private long loanAgreementId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_loanagrmnt_id",referencedColumnName = "loanAgreementId")
	LoanAgreement loanAgreement;
}
