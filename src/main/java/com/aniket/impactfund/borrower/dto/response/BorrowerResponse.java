package com.aniket.impactfund.borrower.dto.response;

import com.aniket.impactfund.borrower.enums.EmploymentType;
import com.aniket.impactfund.borrower.enums.VerificationStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record BorrowerResponse (
        UUID uuid,

        String fullName,

        String occupation,

        BigDecimal monthlyIncome,

        EmploymentType employmentType,

        String city,

        String state,

        String country,

        Integer creditScore,

        VerificationStatus verificationStatus
) { }
