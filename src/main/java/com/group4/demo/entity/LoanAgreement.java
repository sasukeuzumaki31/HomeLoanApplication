package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class LoanAgreement {
    private long loanAgreementId;
    private long loanApplicationId;

}
