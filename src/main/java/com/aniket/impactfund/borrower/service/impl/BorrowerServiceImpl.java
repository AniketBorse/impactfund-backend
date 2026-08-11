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
import com.aniket.impactfund.user.entity.User;
import com.aniket.impactfund.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final UserRepository userRepository;
    private final BorrowerMapper borrowerMapper;

    private boolean userExists(User user) {
        return borrowerRepository.existsByUser(user);
    }

    private void validateRequest(CreateBorrowerRequest request) {
        if (borrowerRepository.existsByPanNumber(request.panNumber())) {
            throw new DuplicateResourceException(
                    "PAN",
                    "pan",
                    request.panNumber()
            );
        }

        if (borrowerRepository.existsByAadhaarNumber(request.aadhaarNumber())) {
            throw new DuplicateResourceException(
                    "Aadhaar",
                    "Aadhaar",
                    request.aadhaarNumber()
            );
        }
    }

    @Override
    public BorrowerResponse createBorrower(CreateBorrowerRequest request) {
        User currentUser = getCurrentUser();

        if (userExists(currentUser)) {
            throw new DuplicateResourceException(
                "Borrower",
                "userId",
                currentUser.getId()
            );
        }

        validateRequest(request);
        Borrower borrower = borrowerMapper.toEntity(request, currentUser);
        System.out.println("Borrower ID before save = " + borrower.getId());
        Borrower savedBorrower = borrowerRepository.save(borrower);
        return borrowerMapper.toResponse(savedBorrower);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User",
                                "email",
                                email
                        ));
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowerResponse getCurrentBorrower() {
        User currentUser = getCurrentUser();
        Borrower borrower = borrowerRepository.findByUser(currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower",
                                "user",
                                currentUser.getId()
                        ));
        return borrowerMapper.toResponse(borrower);
    }

    @Override
    public BorrowerResponse getMyProfile() {
        return null;
    }

    @Override
    public BorrowerResponse updateBorrower(UpdateBorrowerRequest request) {
        User currentUser = getCurrentUser();
        Borrower borrower = borrowerRepository.findByUser(currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower",
                                "User",
                                currentUser.getId()
        ));
        borrowerMapper.updateEntity(borrower, request);
        Borrower updatedBorrower = borrowerRepository.save(borrower);
        return borrowerMapper.toResponse(updatedBorrower);
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowerResponse getBorrower(UUID uuid) {
        Borrower borrower = borrowerRepository.findByUuid(uuid)
                .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Borrower",
                        "user",
                        uuid
                ));

        return borrowerMapper.toResponse(borrower);
    }
}
