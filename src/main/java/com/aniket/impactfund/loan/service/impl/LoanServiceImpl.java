package com.aniket.impactfund.loan.service.impl;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.borrower.repository.BorrowerRepository;
import com.aniket.impactfund.common.exception.ResourceNotFoundException;
import com.aniket.impactfund.loan.dto.request.CreateLoanRequest;
import com.aniket.impactfund.loan.dto.response.LoanResponse;
import com.aniket.impactfund.loan.entity.Loan;
import com.aniket.impactfund.loan.enums.LoanStatus;
import com.aniket.impactfund.loan.mapper.LoanMapper;
import com.aniket.impactfund.loan.repository.LoanRepository;
import com.aniket.impactfund.loan.service.LoanService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LoanServiceImpl implements LoanService {
    private LoanRepository loanRepository;
    private BorrowerRepository borrowerRepository;
    private LoanMapper loanMapper;

    @Override
    public LoanResponse createLoan(CreateLoanRequest request) {
        Borrower borrower = borrowerRepository.findByUuid(request.borrowerUuid())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower",
                                "UUID",
                                request.borrowerUuid()
                        ));

        Loan loan = loanMapper.toEntity(request, borrower);
        loan.setApplicationDate(LocalDate.now());
        loan.setStatus(LoanStatus.PENDING);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponse getLoanByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return List.of();
    }

    @Override
    public List<LoanResponse> getLoansByBorrower(UUID borrowerUuid) {
        return List.of();
    }
}
