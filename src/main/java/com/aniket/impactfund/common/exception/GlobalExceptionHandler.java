package com.aniket.impactfund.common.exception;

import com.aniket.impactfund.common.response.ApiResponse;
import com.aniket.impactfund.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResourceException(DuplicateResourceException ex) {
        ApiResponse<Void> response =
                new ApiResponse<>(
                false,
                ex.getMessage(),
                null,
                        List.of(
                                new ErrorResponse(
                                        ex.getField(),
                                        ex.getDetailedMessage()
                                )
                        ),
                        Instant.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ApiResponse<Void> response =
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null,
                        List.of(
                                new ErrorResponse(
                                        ex.getField(),
                                        ex.getDetailedMessage()
                                )
                        ),
                        Instant.now()
                );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorResponse(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        ApiResponse<Void> response = new ApiResponse<>(
                false,
                "Validation failed.",
                null,
                errors,
                Instant.now()
        );

        return ResponseEntity.badRequest().body(response);
    }
}
