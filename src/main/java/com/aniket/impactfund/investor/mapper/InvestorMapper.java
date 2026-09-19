package com.aniket.impactfund.investor.mapper;

import com.aniket.impactfund.investor.dto.request.CreateInvestorRequest;
import com.aniket.impactfund.investor.dto.response.InvestorResponse;
import com.aniket.impactfund.investor.entity.Investor;
import com.aniket.impactfund.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvestorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "verificationStatus", ignore = true)
    Investor toEntity(CreateInvestorRequest request, User user);

    @Mapping(target = "uuid", source = "uuid")
    @Mapping(target = "userUuid", source = "user.uuid")
    InvestorResponse toResponse(Investor investor);
}
