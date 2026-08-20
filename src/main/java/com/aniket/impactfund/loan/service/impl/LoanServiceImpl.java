package com.aniket.impactfund.loan.service.impl;

import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.borrower.repository.BorrowerRepository;
import com.aniket.impactfund.common.exception.ResourceAlreadyExistsException;
import com.aniket.impactfund.common.exception.ResourceNotFoundException;
import com.aniket.impactfund.loan.dto.request.CreateLoanRequest;
import com.aniket.impactfund.loan.dto.request.RejectionLoanRequest;
import com.aniket.impactfund.loan.dto.response.LoanResponse;
import com.aniket.impactfund.loan.entity.Loan;
import com.aniket.impactfund.loan.enums.LoanStatus;
import com.aniket.impactfund.loan.mapper.LoanMapper;
import com.aniket.impactfund.loan.repository.LoanRepository;
import com.aniket.impactfund.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BorrowerRepository borrowerRepository;
    private final LoanMapper loanMapper;

    private static final List<LoanStatus> ACTIVE_LOAN_STATUSES = List.of(
            LoanStatus.ACTIVE,
            LoanStatus.APPROVED,
            LoanStatus.FUNDED,
            LoanStatus.FUNDING,
            LoanStatus.PENDING
    );

    @Override
    public LoanResponse createLoan(CreateLoanRequest request) {
        Borrower borrower = borrowerRepository.findByUuid(request.borrowerUuid())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower",
                                "UUID",
                                request.borrowerUuid()
                        ));

        boolean duplicatePurpose = loanRepository.existsByBorrowerAndPurposeAndStatusIn(
                borrower,
                request.purpose(),
                ACTIVE_LOAN_STATUSES
        );

        if(duplicatePurpose) {
            throw new ResourceAlreadyExistsException(
                    "Borrower already have an active loan for the purpose",
                    String.valueOf(request.purpose())
            );
        }

        Loan loan = loanMapper.toEntity(request, borrower);
        loan.setApplicationDate(LocalDate.now());
        loan.setStatus(LoanStatus.PENDING);
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanByUuid(UUID uuid) {
        Loan loan = loanRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan",
                                "UUID",
                                uuid
        ));

        return loanMapper.toResponse(loan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByBorrower(UUID borrowerUuid) {
        Borrower borrower = borrowerRepository.findByUuid(borrowerUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower",
                                "UUID",
                                borrowerUuid
                        ));

        return loanRepository.findByBorrower(borrower)
                .stream()
                .map(loanMapper :: toResponse)
                .toList();
    }

    @Override
    public LoanResponse approveLoan(UUID loanUuid) {
        Loan loan = loanRepository.findByUuid(loanUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan",
                                "UUID",
                                loanUuid
                        )
                );

        if(loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved");
        }

        loan.setStatus(LoanStatus.APPROVED);
        loan.setApproveDate(LocalDate.now());

        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponse(savedLoan);
    }

    @Override
    public LoanResponse rejectLoan(UUID loanUuid, RejectionLoanRequest request) {
        Loan loan = loanRepository.findByUuid(loanUuid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan",
                                "UUID",
                                loanUuid
                        )
                );

        if(loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Only pending loans can be approved");
        }

        loan.setStatus(LoanStatus.REJECTED);
        loan.setRejectionReason(request.reason());

        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponse(savedLoan);
    }
}
