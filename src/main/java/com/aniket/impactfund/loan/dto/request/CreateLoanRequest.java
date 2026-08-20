package com.aniket.impactfund.loan.dto.request;

import com.aniket.impactfund.loan.enums.LoanPurpose;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateLoanRequest(

        @NotNull(message = "Borrower UUID is required")
        UUID borrowerUuid,

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1000.00", message = "Loan amount must be at least 1000")
        BigDecimal amount,

        @NotNull(message = "Interest Rate is required")
        @DecimalMin(value = "0.00", message = "Interest rate cannot be negative")
        @DecimalMax(value = "100.00", message = "Interest rate cannot exceed 100")
        BigDecimal interestRate,

        @NotNull(message = "Tenure Months are required")
        @Min(value = 1, message = "Tenure must be at least 1 month")
        Integer tenureMonths,

        @NotNull(message = "Purpose is required")
        LoanPurpose purpose
) { }
