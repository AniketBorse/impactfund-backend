package com.aniket.impactfund.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken
) { }
