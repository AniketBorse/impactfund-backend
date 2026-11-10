package com.aniket.impactfund.investor.service;

import com.aniket.impactfund.investor.dto.request.CreateInvestorRequest;
import com.aniket.impactfund.investor.dto.response.InvestorResponse;

import java.util.UUID;

public interface InvestorService {
    InvestorResponse createInvestor(CreateInvestorRequest request);

    InvestorResponse getMyProfile();

    InvestorResponse getInvestorByUUID(UUID uuid);
}
