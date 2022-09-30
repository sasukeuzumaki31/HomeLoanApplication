package com.group4.demo.entity;

import lombok.*;

import java.time.LocalDate;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoanApplication {
	@Id
	private long applicationId;
	private LocalDate applicationDate;
	private double loanAppliedAmount;
	private double loanApprovedAmount;
	private boolean landVerificationApproval;
	private boolean financeVerificationApproval;
	private boolean adminApproval;
	private String status;

}
