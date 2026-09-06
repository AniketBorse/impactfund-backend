package com.aniket.impactfund.investor.dto.request;

import com.aniket.impactfund.investor.enums.RiskProfile;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.math.BigDecimal;

public record CreateInvestorRequest(
        @NotBlank(message = "Occupation is required")
        String occupation,

        @NotNull(message = "Annual Income is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Annual Income must be greater than 0")
        BigDecimal annualIncome,

        @NotNull(message = "Annual Income is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Investment Capacity must be greater than 0")
        BigDecimal investmentCapacity,

        @NotNull(message = "Risk Profile is required")
        RiskProfile riskProfile
) { }
