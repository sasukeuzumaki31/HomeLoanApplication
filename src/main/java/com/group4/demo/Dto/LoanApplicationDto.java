package com.group4.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDto {

    private long applicationId;
    private LocalDate applicationDate;
    private double loanAppliedAmount;
    private double loanApprovedAmount;
    private boolean landVerificationApproval;
    private boolean financeVerificationApproval;
    private boolean adminApproval;
    private String status;
    private int customerId;
    private int schemeId;
    private double totalAnnualIncome;
    private double monthlyExpenses;
    private double otherMonthlyExpenses;
}
