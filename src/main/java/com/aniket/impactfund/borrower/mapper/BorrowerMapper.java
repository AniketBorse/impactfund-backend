package com.aniket.impactfund.borrower.mapper;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {
    Borrower toEntity(CreateBorrowerRequest request, User user);

    BorrowerResponse toResponse(Borrower borrower);

    void updateEntity(@MappingTarget Borrower borrower, UpdateBorrowerRequest request);
}
