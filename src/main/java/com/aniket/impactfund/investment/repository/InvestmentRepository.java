package com.aniket.impactfund.investment.repository;

import com.aniket.impactfund.investment.entity.Investment;
import com.aniket.impactfund.investment.enums.InvestmentStatus;
import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.loan.entity.Loan;
import com.aniket.impactfund.loan.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Optional<Investment> findByUuid(UUID uuid);

    List<Investment> findByInvestor(Investor investor);

    List<Investment> findByLoan(Loan loan);

    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM Investment i
        WHERE i.loan = :loan
        AND i.status = :status
    """)
    BigDecimal getTotalInvestedAmountByLoan(
        @Param("loan") Loan loan,
        @Param("status") InvestmentStatus status
    );

    @Query("""
        SELECT COALESCE(SUM(i.amount), 0)
        FROM Investment i
        WHERE i.investor = :investor
        AND i.status = :status
    """)
    BigDecimal getTotalInvestedAmountByInvestor(
        @Param("investor") Investor investor,
        @Param("status") InvestmentStatus status
    );
}
