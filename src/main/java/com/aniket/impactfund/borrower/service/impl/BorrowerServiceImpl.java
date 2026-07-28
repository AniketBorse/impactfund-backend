package com.aniket.impactfund.borrower.service.impl;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.mapper.BorrowerMapper;
import com.aniket.impactfund.borrower.repository.BorrowerRepository;
import com.aniket.impactfund.borrower.service.BorrowerService;
import com.aniket.impactfund.user.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final UserRepository userRepository;
    private final BorrowerMapper borrowerMapper;

    @Override
    public BorrowerResponse createBorrower(CreateBorrowerRequest request) {
        return null;
    }

    @Override
    public BorrowerResponse getMyProfile() {
        return null;
    }

    @Override
    public BorrowerResponse updateBorrower(UpdateBorrowerRequest request) {
        return null;
    }

    @Override
    public BorrowerResponse getBorrower(UUID uuid) {
        return null;
    }
}
