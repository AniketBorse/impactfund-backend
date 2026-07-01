package com.aniket.impactfund.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record RegisterUserRequest(
    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @Email
    @NotBlank
    String email,

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    String phoneNumber,

    @NotBlank
    String password,

    @Past
    LocalDate dateOfBirth
) {}
