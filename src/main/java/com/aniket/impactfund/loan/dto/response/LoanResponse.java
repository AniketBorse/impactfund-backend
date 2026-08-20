package com.aniket.impactfund.loan.dto.response;

import com.aniket.impactfund.loan.enums.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanResponse(

        UUID uuid,

        UUID borrowerUuid,

        BigDecimal amount,

        BigDecimal interestRate,

        Integer tenureMonths,

        String purpose,

        LoanStatus status,

        LocalDate applicationDate,

        LocalDate approveDate,

        LocalDate fundedDate,

        String reason
) {
}
