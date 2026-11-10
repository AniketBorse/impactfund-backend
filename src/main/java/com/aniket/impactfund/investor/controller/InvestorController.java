package com.aniket.impactfund.investor.controller;

import com.aniket.impactfund.investor.dto.request.CreateInvestorRequest;
import com.aniket.impactfund.investor.dto.response.InvestorResponse;
import com.aniket.impactfund.investor.service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/investors")
@RequiredArgsConstructor
public class InvestorController {
    private final InvestorService investorService;

    @PostMapping
    public ResponseEntity<InvestorResponse> createInvestor(@Valid @RequestBody CreateInvestorRequest request) {
        InvestorResponse response = investorService.createInvestor(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<InvestorResponse> getProfile() {
        InvestorResponse response = investorService.getMyProfile();
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InvestorResponse> getInvestorByUUID(@PathVariable UUID uuid) {
        InvestorResponse response = investorService.getInvestorByUUID(uuid);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
