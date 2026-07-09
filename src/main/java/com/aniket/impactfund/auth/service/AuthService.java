package com.aniket.impactfund.auth.service;

import com.aniket.impactfund.auth.dto.request.LoginRequest;
import com.aniket.impactfund.auth.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
