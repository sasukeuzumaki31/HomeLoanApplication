package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public  class LoanAgreement {
    @Id
    private long loanAgreementId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_loan_id",referencedColumnName = "applicationId")
    LoanApplication loanApplication;
}
