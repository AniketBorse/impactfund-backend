package com.aniket.impactfund.common.response;

public record ErrorResponse(
        String field,
        String message
) {
}
