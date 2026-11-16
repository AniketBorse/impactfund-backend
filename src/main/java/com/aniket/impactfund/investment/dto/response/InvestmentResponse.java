package com.aniket.impactfund.investment.dto.response;

import com.aniket.impactfund.investment.enums.InvestmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InvestmentResponse(
        UUID uuid,
        UUID investorUuid,
        UUID loanUuid,
        BigDecimal amount,
        InvestmentStatus investmentStatus,
        LocalDate investmentDate
) { }
