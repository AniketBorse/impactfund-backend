package com.aniket.impactfund.borrower.service;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;

import java.util.UUID;

public interface BorrowerService {
    BorrowerResponse createBorrower(CreateBorrowerRequest request);

    BorrowerResponse getCurrentBorrower();

    BorrowerResponse updateBorrower(UpdateBorrowerRequest request);

    BorrowerResponse getBorrower(UUID uuid);
}
