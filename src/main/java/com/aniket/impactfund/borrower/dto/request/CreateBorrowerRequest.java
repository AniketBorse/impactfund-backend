package com.aniket.impactfund.borrower.dto.request;

import com.aniket.impactfund.borrower.enums.EmploymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

import java.math.BigDecimal;

public record CreateBorrowerRequest(
        @NotBlank
        String occupation,

        @NotNull
        @Positive
        BigDecimal monthlyIncome,

        @NotNull
        EmploymentType employmentType,

        @NotBlank
        @Pattern(regexp = "\\d{12}")
        String aadhaarNumber,

        @NotBlank
        @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]")
        String panNumber,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country
) { }
