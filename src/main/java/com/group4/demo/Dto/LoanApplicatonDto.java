package com.group4.demo.Dto;

import com.group4.demo.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicatonDto {

    private long applicationId;
    private LocalDate applicationDate;
    private double loanAppliedAmount;
    private double loanApprovedAmount;
    private boolean landVerificationApproval;
    private boolean financeVerificationApproval;
    private boolean adminApproval;
    private String status;
    private int customerId;
}
