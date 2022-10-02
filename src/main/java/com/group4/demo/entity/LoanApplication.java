package com.group4.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long applicationId;
    private LocalDate applicationDate;
    private double loanAppliedAmount;
    private double loanApprovedAmount;
    private boolean landVerificationApproval;
    private boolean financeVerificationApproval;
    private boolean adminApproval;
    private String status;
    @NotNull
    private double totalAnnualIncome;
    @NotNull
    private double monthlyExpenses;
    @NotNull
    private double otherMonthlyExpenses;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "scheme_scheme_id", referencedColumnName = "schemeId")
     Scheme scheme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_customer_id", referencedColumnName = "userId")
    @JsonBackReference
    Customer customer;

}
