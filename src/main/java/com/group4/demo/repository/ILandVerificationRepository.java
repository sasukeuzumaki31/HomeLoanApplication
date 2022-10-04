package com.group4.demo.repository;

import com.group4.demo.entity.LandVerificationOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILandVerificationRepository extends JpaRepository<LandVerificationOfficer,Integer> {
    LandVerificationOfficer findByOfficeName(String officeName);

    LandVerificationOfficer findByOfficeContact(String officeContact);
}
