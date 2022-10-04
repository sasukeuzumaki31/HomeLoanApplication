package com.group4.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationDto {

    private long applicationId;
    private LocalDate applicationDate;
    @Min(value = 500000,message = "Minimum home loan application value should be 500000")
    private double loanAppliedAmount;
    private double loanApprovedAmount;
    private boolean landVerificationApproval;
    private boolean financeVerificationApproval;
    private boolean adminApproval;
    private String status;
    @Min(value = 0,message = "customer id cannot be negative")
    private int customerId;
    @Min(value = 0,message = "scheme id cannot be negative")
    private int schemeId;
    @Min(value = 0,message = "Cannot be less than 0")
        private double totalAnnualIncome;
    @Min(value = 0,message = "Cannot be less than 0")
    private double monthlyExpenses;
    @Min(value = 0,message = "Cannot be less than 0")
    private double otherMonthlyExpenses;
}
