package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long loanAgreementId;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="fk_emi_id",referencedColumnName = "emiId")
    EMI emi;
}
