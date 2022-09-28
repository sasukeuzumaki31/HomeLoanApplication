package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public  class LoanAgreement {
    @Id
    private long loanAgreementId;
    private long loanApplicationId;

}
