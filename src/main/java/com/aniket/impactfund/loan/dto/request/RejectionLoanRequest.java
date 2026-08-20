package com.aniket.impactfund.loan.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RejectionLoanRequest(

        @NotBlank(message = "Reason is Required")
        @Size(max=250, message = "Reason should be maximum 250 characters")
        String reason
) { }
