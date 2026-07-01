package com.aniket.impactfund.user.service;

import com.aniket.impactfund.user.dto.request.RegisterUserRequest;
import com.aniket.impactfund.user.dto.response.UserResponse;

public interface UserService {
    UserResponse register(RegisterUserRequest request);
}
