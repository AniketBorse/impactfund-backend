package com.aniket.impactfund.investment.entity;

import com.aniket.impactfund.common.audit.BaseEntity;
import com.aniket.impactfund.investment.enums.InvestmentStatus;
import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.loan.entity.Loan;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id", referencedColumnName = "id", nullable = false)
    private Investor investor;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "loan_id", referencedColumnName = "id", nullable = false)
    private Loan loan;

    @Column(name = "amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvestmentStatus status = InvestmentStatus.PENDING;

    @Column(name = "investment_date", nullable = false)
    private LocalDate investmentDate;
}
