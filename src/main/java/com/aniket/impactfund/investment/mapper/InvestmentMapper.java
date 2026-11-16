package com.aniket.impactfund.investment.mapper;

import com.aniket.impactfund.investment.dto.response.InvestmentResponse;
import com.aniket.impactfund.investment.entity.Investment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    @Mapping(target = "investorUuid", source = "investor.uuid")
    @Mapping(target = "loanUuid", source = "loan.uuid")
    InvestmentResponse toResponse(Investment investment);
}
