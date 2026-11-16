package com.aniket.impactfund.investment.service;

import com.aniket.impactfund.investment.dto.request.CreateInvestmentRequest;
import com.aniket.impactfund.investment.dto.response.InvestmentResponse;

import java.util.List;
import java.util.UUID;

public interface InvestmentService {
    InvestmentResponse createInvestment(CreateInvestmentRequest request);

    List<InvestmentResponse> getMyInvestments();

    InvestmentResponse getInvestmentByUUID(UUID uuid);

}
