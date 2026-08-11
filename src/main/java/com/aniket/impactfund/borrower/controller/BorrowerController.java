package com.aniket.impactfund.borrower.controller;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.service.BorrowerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowers")
@RequiredArgsConstructor
public class BorrowerController {
    private final BorrowerService borrowerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowerResponse createBorrower(@Valid @RequestBody CreateBorrowerRequest request) {
        return borrowerService.createBorrower(request);
    }

    @GetMapping("/me")
    public ResponseEntity<BorrowerResponse> getCurrentBorrower() {
        return ResponseEntity.ok(
                borrowerService.getCurrentBorrower()
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BorrowerResponse> getBorrowerByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.ok(
                borrowerService.getBorrower(uuid)
        );
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public BorrowerResponse updateBorrower(@Valid @RequestBody UpdateBorrowerRequest request) {
        return borrowerService.updateBorrower(request);
    }
}
