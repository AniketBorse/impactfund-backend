package com.aniket.impactfund.investor.dto.response;

import com.aniket.impactfund.investor.enums.RiskProfile;
import com.aniket.impactfund.common.enums.VerificationStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record InvestorResponse(
        UUID uuid,

        UUID userUuid,

        String occupation,

        BigDecimal annualIncome,

        BigDecimal investmentCapacity,

        RiskProfile riskProfile,

        VerificationStatus verificationStatus
) { }
