package com.aniket.impactfund.investor.entity;

import com.aniket.impactfund.common.audit.BaseEntity;
import com.aniket.impactfund.investor.enums.RiskProfile;
import com.aniket.impactfund.investor.enums.VerificationStatus;
import com.aniket.impactfund.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Investor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    @Column(nullable = false)
    private String occupation;

    @Column(name = "annual_income", precision = 15, scale = 2)
    private BigDecimal annualIncome;

    @Column(name = "investment_capacity", precision = 15, scale = 2)
    private BigDecimal investmentCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_profile", nullable = false)
    private RiskProfile riskProfile;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "verified_at")
    private Instant verifiedAt;
}
