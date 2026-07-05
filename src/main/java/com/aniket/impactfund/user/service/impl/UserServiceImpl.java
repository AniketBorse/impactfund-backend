package com.aniket.impactfund.user.service.impl;

import com.aniket.impactfund.common.exception.DuplicateResourceException;
import com.aniket.impactfund.user.dto.request.RegisterUserRequest;
import com.aniket.impactfund.user.dto.response.UserResponse;
import com.aniket.impactfund.user.entity.User;
import com.aniket.impactfund.user.enums.UserStatus;
import com.aniket.impactfund.user.repository.UserRepository;
import com.aniket.impactfund.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void validateRequest(RegisterUserRequest request) {
        if(userRepository.existsByEmail(request.email().trim().toLowerCase())) {
            throw new DuplicateResourceException("User",
                    "email",
                    request.email()
            );
        }

        if(userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicateResourceException("User",
                    "phoneNumber",
                    request.phoneNumber()
            );
        }
    }

    private User buildUser(RegisterUserRequest request) {
        return User.builder()
            .firstName(request.firstName().trim())
            .lastName(request.lastName().trim())
            .email(request.email().trim().toLowerCase())
            .phoneNumber(request.phoneNumber())
            .password(passwordEncoder.encode(request.password()))
            .dateOfBirth(request.dateOfBirth())
            .status(UserStatus.PENDING_VERIFICATION)
            .emailVerified(false)
            .phoneVerified(false)
            .build();
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhoneNumber()
        );
    }

    @Override
    public UserResponse register(RegisterUserRequest request) {
        validateRequest(request);
        User user = buildUser(request);
        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }
}
