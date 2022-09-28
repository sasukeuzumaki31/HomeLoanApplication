package repository;

import com.group4.demo.entity.LoanAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanAgreementRepository extends JpaRepository<LoanAgreement,Long> {


}
