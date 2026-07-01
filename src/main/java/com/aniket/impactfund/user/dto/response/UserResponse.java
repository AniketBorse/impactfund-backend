package com.aniket.impactfund.user.dto.response;

import java.util.UUID;

public record UserResponse(
    UUID id,

    String firstName,

    String lastName,

    String email,

    String phoneNumber
) { }

