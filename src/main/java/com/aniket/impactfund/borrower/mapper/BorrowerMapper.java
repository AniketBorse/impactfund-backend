package com.aniket.impactfund.borrower.mapper;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {
    @Mapping(target = "id", ignore = true)
    Borrower toEntity(CreateBorrowerRequest request, User user);

    @Mapping(
            target = "fullName",
            expression = "java(borrower.getUser().getFirstName() + \" \" + borrower.getUser().getLastName())"
    )
    BorrowerResponse toResponse(Borrower borrower);

    void updateEntity(@MappingTarget Borrower borrower, UpdateBorrowerRequest request);
}
