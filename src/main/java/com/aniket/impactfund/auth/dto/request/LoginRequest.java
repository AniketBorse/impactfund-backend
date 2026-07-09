package com.aniket.impactfund.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email is Required")
        @Email(message = "Please enter Valid Email")
        String emailId,

        @NotBlank(message = "Password is Required")
        String password
) { }
