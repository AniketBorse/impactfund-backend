package com.aniket.impactfund.loan.repository;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.loan.entity.Loan;
import com.aniket.impactfund.loan.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByBorrower(Borrower borrower);

    List<Loan> findByBorrowerAndStatus(
            Borrower borrower,
            LoanStatus status
    );
}
