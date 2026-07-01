package com.aniket.impactfund.auth.controller;

import com.aniket.impactfund.user.dto.request.RegisterUserRequest;
import com.aniket.impactfund.user.dto.response.UserResponse;
import com.aniket.impactfund.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }
}
