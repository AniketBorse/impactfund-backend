package com.aniket.impactfund.investor.service.impl;

import com.aniket.impactfund.common.enums.VerificationStatus;
import com.aniket.impactfund.common.exception.DuplicateResourceException;
import com.aniket.impactfund.common.exception.ResourceNotFoundException;
import com.aniket.impactfund.common.security.CurrentUserService;
import com.aniket.impactfund.investor.dto.request.CreateInvestorRequest;
import com.aniket.impactfund.investor.dto.response.InvestorResponse;
import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.investor.mapper.InvestorMapper;
import com.aniket.impactfund.investor.repository.InvestorRepository;
import com.aniket.impactfund.investor.service.InvestorService;
import com.aniket.impactfund.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InvestorServiceImpl implements InvestorService {
    private final CurrentUserService currentUserService;
    private final InvestorRepository investorRepository;
    private final InvestorMapper investorMapper;

    @Override
    public InvestorResponse createInvestor(CreateInvestorRequest request) {
        User user = currentUserService.getCurrentUser();

        if(investorRepository.existsByUser(user)) {
            throw new DuplicateResourceException("Investor", "ID", user.getId());
        }

        Investor investor = investorMapper.toEntity(request, user);
        investor.setVerificationStatus(VerificationStatus.PENDING);
        Investor savedInvestor = investorRepository.save(investor);

        return investorMapper.toResponse(savedInvestor);
    }

    @Override
    @Transactional(readOnly = true)
    public InvestorResponse getMyProfile() {
        User user = currentUserService.getCurrentUser();

        Investor investor = investorRepository.findByUser(user).orElseThrow(() ->
                new ResourceNotFoundException("Investor", "User ID", user.getId())
        );

        return investorMapper.toResponse(investor);
    }

    @Override
    @Transactional(readOnly = true)
    public InvestorResponse getInvestorByUUID(UUID uuid) {

        Investor investor = investorRepository.findByUuid(uuid).orElseThrow(() ->
                new ResourceNotFoundException("Investor", "User ID", uuid)
        );

        return investorMapper.toResponse(investor);
    }
}
