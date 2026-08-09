package com.aniket.impactfund.borrower.service.impl;

import com.aniket.impactfund.borrower.dto.request.CreateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.request.UpdateBorrowerRequest;
import com.aniket.impactfund.borrower.dto.response.BorrowerResponse;
import com.aniket.impactfund.borrower.entity.Borrower;
import com.aniket.impactfund.borrower.mapper.BorrowerMapper;
import com.aniket.impactfund.borrower.repository.BorrowerRepository;
import com.aniket.impactfund.borrower.service.BorrowerService;
import com.aniket.impactfund.common.exception.DuplicateResourceException;
import com.aniket.impactfund.common.exception.ResourceNotFoundException;
import com.aniket.impactfund.common.security.CurrentUserService;
import com.aniket.impactfund.user.entity.User;
import com.aniket.impactfund.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final UserRepository userRepository;
    private final BorrowerMapper borrowerMapper;

    private boolean userExists(User user) {
        return borrowerRepository.existsByUser(user);
    }

    private void validateRequest(CreateBorrowerRequest request) {
        if (borrowerRepository.existsByPanNumber(request.panNumber())) {
            throw new RuntimeException("PAN number already exists.");
        }

        if (borrowerRepository.existsByAadhaarNumber(request.aadhaarNumber())) {
            throw new RuntimeException("Aadhaar number already exists.");
        }
    }

    @Override
    public BorrowerResponse createBorrower(CreateBorrowerRequest request) {
        User currentUser = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("User", "id", 1L));

        if (userExists(currentUser)) {
            throw new DuplicateResourceException(
                "Borrower",
                "userId",
                currentUser.getId()
            );
        }

        validateRequest(request);
        Borrower borrower = borrowerMapper.toEntity(request, currentUser);
        Borrower savedBorrower = borrowerRepository.save(borrower);
        return borrowerMapper.toResponse(savedBorrower);
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
