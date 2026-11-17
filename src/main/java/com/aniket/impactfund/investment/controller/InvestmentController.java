package com.aniket.impactfund.investment.controller;

import com.aniket.impactfund.investment.dto.request.CreateInvestmentRequest;
import com.aniket.impactfund.investment.dto.response.InvestmentResponse;
import com.aniket.impactfund.investment.service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/investments")
public class InvestmentController {
    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<InvestmentResponse> createInvestment(@Valid @RequestBody CreateInvestmentRequest request) {
        InvestmentResponse response = investmentService.createInvestment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<InvestmentResponse>> getMyInvestments() {
        List<InvestmentResponse> response = investmentService.getMyInvestments();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InvestmentResponse> getInvestmentByUUID(@PathVariable UUID uuid) {
        InvestmentResponse response = investmentService.getInvestmentByUUID(uuid);

        return ResponseEntity.ok(response);
    }
}
