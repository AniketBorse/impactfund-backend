package com.aniket.impactfund.loan.entity;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.common.audit.BaseEntity;
import com.aniket.impactfund.loan.enums.LoanPurpose;
import com.aniket.impactfund.loan.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "loans")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "tenure_months", nullable = false)
    private Integer tenureMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanPurpose purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private LoanStatus status = LoanStatus.PENDING;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "approve_date", nullable = false)
    private LocalDate approveDate;

    @Column(name = "funded_date", nullable = false)
    private LocalDate fundedDate;
}
