package com.aniket.impactfund.borrower.dto.request;

import com.aniket.impactfund.borrower.enums.EmploymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateBorrowerRequest(
        @NotBlank
        String occupation,

        @NotNull
        @Positive
        BigDecimal monthlyIncome,

        @NotNull
        EmploymentType employmentType,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country
) { }
