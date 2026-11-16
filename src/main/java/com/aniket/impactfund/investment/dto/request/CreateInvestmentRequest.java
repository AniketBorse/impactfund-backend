package com.aniket.impactfund.investment.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateInvestmentRequest(

    @NotNull(message = "Loan UUID Required")
    UUID loanUuid,

    @NotNull(message = "Investment amount is required")
    @DecimalMin(value = "0.01", message = "Investment amount must be greater than zero")
    @Digits(integer = 12, fraction = 2, message = "Investment amount must have at most 12 integer digits and 2 decimal places")
    BigDecimal amount
) { }
