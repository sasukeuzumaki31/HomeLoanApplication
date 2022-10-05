package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.EMI;
import com.group4.demo.entity.LoanAgreement;
import com.group4.demo.repository.ILoanAgreementRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ILoanAgreementServiceImplTest {
    @MockBean
    private ILoanAgreementRepository iLoanAgreementRepository;

    @Autowired
    private ILoanAgreementServiceImpl iLoanAgreementServiceImpl;


    @Test
    void testRetrieveAllLoanAgreement() {
        ArrayList<LoanAgreement> loanAgreementList = new ArrayList<>();
        when(iLoanAgreementRepository.findAll()).thenReturn(loanAgreementList);
        List<LoanAgreement> actualRetrieveAllLoanAgreementResult = iLoanAgreementServiceImpl.retrieveAllLoanAgreement();
        assertSame(loanAgreementList, actualRetrieveAllLoanAgreementResult);
        assertTrue(actualRetrieveAllLoanAgreementResult.isEmpty());
        verify(iLoanAgreementRepository).findAll();
    }


    @Test
    void testRetrieveAgreementById() throws ResourceNotFoundException {
        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);
        Optional<LoanAgreement> ofResult = Optional.of(loanAgreement);
        when(iLoanAgreementRepository.findById(any())).thenReturn(ofResult);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.retrieveAgreementById(123L));
        verify(iLoanAgreementRepository).findById(any());
    }


    @Test
    void testRetrieveAgreementById2() {
        when(iLoanAgreementRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iLoanAgreementServiceImpl.retrieveAgreementById(123L));
        verify(iLoanAgreementRepository).findById(any());
    }


    @Test
    void testAddLoanAgreement() {
        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);
        when(iLoanAgreementRepository.save(any())).thenReturn(loanAgreement);

        EMI emi1 = new EMI();
        emi1.setDeuDate(LocalDate.ofEpochDay(1L));
        emi1.setEmiAmount(10.0d);
        emi1.setEmiId(123L);
        emi1.setInterestAmount(10.0d);
        emi1.setLoanAmount(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setEmi(emi1);
        loanAgreement1.setLoanAgreementId(123L);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.addLoanAgreement(loanAgreement1));
        verify(iLoanAgreementRepository).save(any());
    }


    @Test
    void testUpdateLoanAgreement() {
        EMI emi = new EMI();
        emi.setDeuDate(LocalDate.ofEpochDay(1L));
        emi.setEmiAmount(10.0d);
        emi.setEmiId(123L);
        emi.setInterestAmount(10.0d);
        emi.setLoanAmount(10.0d);

        LoanAgreement loanAgreement = new LoanAgreement();
        loanAgreement.setEmi(emi);
        loanAgreement.setLoanAgreementId(123L);
        when(iLoanAgreementRepository.save(any())).thenReturn(loanAgreement);

        EMI emi1 = new EMI();
        emi1.setDeuDate(LocalDate.ofEpochDay(1L));
        emi1.setEmiAmount(10.0d);
        emi1.setEmiId(123L);
        emi1.setInterestAmount(10.0d);
        emi1.setLoanAmount(10.0d);

        LoanAgreement loanAgreement1 = new LoanAgreement();
        loanAgreement1.setEmi(emi1);
        loanAgreement1.setLoanAgreementId(123L);
        assertSame(loanAgreement, iLoanAgreementServiceImpl.updateLoanAgreement(loanAgreement1));
        verify(iLoanAgreementRepository).save(any());
    }
}

