package com.aniket.impactfund.loan.controller;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.loan.dto.request.CreateLoanRequest;
import com.aniket.impactfund.loan.dto.request.RejectionLoanRequest;
import com.aniket.impactfund.loan.dto.response.LoanResponse;
import com.aniket.impactfund.loan.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody @Valid CreateLoanRequest request) {
        LoanResponse loanResponse = loanService.createLoan(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanResponse);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<LoanResponse> getLoanByUuid(@PathVariable UUID uuid) {
        LoanResponse loanResponse = loanService.getLoanByUuid(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanResponse);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> responseList = loanService.getAllLoans();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseList);
    }

    @GetMapping("/borrower/{borrowerUuid}")
    public ResponseEntity<List<LoanResponse>> getLoanByBorrowerUuid(@PathVariable UUID borrowerUuid) {
        List<LoanResponse> responseList = loanService.getLoansByBorrower(borrowerUuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseList);
    }

    @PostMapping("/{uuid}/approve")
    public ResponseEntity<LoanResponse> approveLoan(@PathVariable UUID uuid) {
        LoanResponse response = loanService.approveLoan(uuid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/{uuid}/reject")
    public ResponseEntity<LoanResponse> rejectLoan(@PathVariable UUID uuid, @Valid @RequestBody RejectionLoanRequest request) {
        LoanResponse response = loanService.rejectLoan(uuid, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
