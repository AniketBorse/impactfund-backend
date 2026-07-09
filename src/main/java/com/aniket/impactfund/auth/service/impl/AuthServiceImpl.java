package com.aniket.impactfund.auth.service.impl;

import com.aniket.impactfund.auth.dto.request.LoginRequest;
import com.aniket.impactfund.auth.dto.response.LoginResponse;
import com.aniket.impactfund.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponse login(LoginRequest request) {
        return new LoginResponse(
                "",
                ""
        );
    }
}
