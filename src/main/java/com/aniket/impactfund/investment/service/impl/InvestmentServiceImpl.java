package com.aniket.impactfund.investment.service.impl;

import com.aniket.impactfund.common.enums.VerificationStatus;
import com.aniket.impactfund.common.exception.ResourceNotFoundException;
import com.aniket.impactfund.common.security.CurrentUserService;
import com.aniket.impactfund.investment.dto.request.CreateInvestmentRequest;
import com.aniket.impactfund.investment.dto.response.InvestmentResponse;
import com.aniket.impactfund.investment.entity.Investment;
import com.aniket.impactfund.investment.enums.InvestmentStatus;
import com.aniket.impactfund.investment.mapper.InvestmentMapper;
import com.aniket.impactfund.investment.repository.InvestmentRepository;
import com.aniket.impactfund.investment.service.InvestmentService;
import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.investor.repository.InvestorRepository;
import com.aniket.impactfund.loan.entity.Loan;
import com.aniket.impactfund.loan.enums.LoanStatus;
import com.aniket.impactfund.loan.repository.LoanRepository;
import com.aniket.impactfund.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {
    private final InvestmentMapper investmentMapper;
    private final InvestorRepository investorRepository;
    private final LoanRepository loanRepository;
    private final CurrentUserService currentUserService;
    private final InvestmentRepository investmentRepository;

    @Override
    public InvestmentResponse createInvestment(CreateInvestmentRequest request) {
        User user = currentUserService.getCurrentUser();

        Investor investor = investorRepository.findByUser(user).orElseThrow(() ->
            new ResourceNotFoundException("Investor", "User ID", user.getId())
        );

        if(investor.getVerificationStatus() != VerificationStatus.VERIFIED) {
            throw new IllegalArgumentException("Investor must be verified before making investments");
        }

        Loan loan = loanRepository.findByUuid(request.loanUuid()).orElseThrow(() ->
                new ResourceNotFoundException("Loan", "Loan ID", request.loanUuid())
        );

        if(loan.getStatus() != LoanStatus.FUNDING) {
            throw new IllegalArgumentException("The loan is not currently accepting the investments");
        }

        BigDecimal confirmedLoanAmount = investmentRepository.getTotalInvestedAmountByLoan(loan, InvestmentStatus.CONFIRMED);

        BigDecimal pendingLoanAmount = investmentRepository.getTotalInvestedAmountByLoan(loan, InvestmentStatus.PENDING);

        BigDecimal totalLoanCommitments = confirmedLoanAmount.add(pendingLoanAmount);

        BigDecimal remainingLoanAmount = loan.getAmount().subtract(totalLoanCommitments);

        if(request.amount().compareTo(remainingLoanAmount) > 0) {
            throw new IllegalArgumentException("Investment amount exceeds than the remaining loan amount");
        }

        BigDecimal confirmedInvestorAmount = investmentRepository.getTotalInvestedAmountByInvestor(investor, InvestmentStatus.CONFIRMED);

        BigDecimal pendingInvestorAmount = investmentRepository.getTotalInvestedAmountByInvestor(investor, InvestmentStatus.PENDING);

        BigDecimal totalInvestorCommitments = confirmedInvestorAmount.add(pendingInvestorAmount);

        BigDecimal availableCapacity = loan.getAmount().subtract(totalInvestorCommitments);

        if(request.amount().compareTo(availableCapacity) > 0) {
            throw new IllegalArgumentException("Investment amount exceeds than the investment capacity");
        }

        Investment investment = Investment.builder()
                .investor(investor)
                .loan(loan)
                .amount(request.amount())
                .status(InvestmentStatus.PENDING)
                .investmentDate(LocalDate.now())
                .build();

        Investment savedInvestment = investmentRepository.save(investment);

        return investmentMapper.toResponse(savedInvestment);
    }

    @Override
    public List<InvestmentResponse> getMyInvestments() {
        User user = currentUserService.getCurrentUser();

        Investor investor = investorRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Investor",
                                "User ID",
                                user.getId()
                        )
        );


        return investmentRepository.findByInvestor(investor)
                .stream()
                .map(investmentMapper :: toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InvestmentResponse getInvestmentByUUID(UUID uuid) {
        User user = currentUserService.getCurrentUser();

        Investor investor = investorRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Investor", "User ID", user.getId())
        );

        Investment investment = investmentRepository.findByUuid(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Investment", "User ID", uuid)
        );

        if(!investment.getInvestor().getId().equals(investor.getId())) {
            throw new ResourceNotFoundException("Investment", "User ID", uuid);
        }

        return investmentMapper.toResponse(investment);
    }
}
