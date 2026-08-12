package com.aniket.impactfund.loan.mapper;

import com.aniket.impactfund.loan.dto.request.CreateLoanRequest;
import com.aniket.impactfund.loan.dto.response.LoanResponse;
import com.aniket.impactfund.loan.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "borrower", source = "borrower")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "approveDate", ignore = true)
    @Mapping(target = "fundedDate", ignore = true)
    Loan toEntity(CreateLoanRequest request);

    @Mapping(target = "uuid", source = "loan.uuid")
    @Mapping(target = "borrowerUuid", source = "borrower.id")
    LoanResponse toResponse(Loan loan);
}
