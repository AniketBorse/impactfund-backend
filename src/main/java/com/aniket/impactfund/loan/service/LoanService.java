package com.aniket.impactfund.loan.service;

import com.aniket.impactfund.loan.dto.request.CreateLoanRequest;
import com.aniket.impactfund.loan.dto.request.RejectionLoanRequest;
import com.aniket.impactfund.loan.dto.response.LoanResponse;

import java.util.List;
import java.util.UUID;

public interface LoanService {
    LoanResponse createLoan(CreateLoanRequest request);

    LoanResponse getLoanByUuid(UUID uuid);

    List<LoanResponse> getAllLoans();

    List<LoanResponse> getLoansByBorrower(UUID borrowerUuid);

    LoanResponse approveLoan(UUID loanUuid);

    LoanResponse rejectLoan(UUID loanUuid, RejectionLoanRequest request);
}
