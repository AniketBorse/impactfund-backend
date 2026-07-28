package com.aniket.impactfund.borrower.mapper;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.user.entity.User;

public abstract class BorrowerMapper {
    abstract Borrower toEntity(CreateBorrowerRequest request, User user);

    abstract BorrowerResponse toResponse(Borrower borrower);

    abstract void updateEntity(Borrower borrower, UpdateBorrowerRequest request);
}
