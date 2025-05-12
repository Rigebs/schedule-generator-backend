package com.rige.controllers;

import com.rige.dtos.request.LoginRequest;
import com.rige.dtos.response.ApiResponse;
import com.rige.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequest userRequest) {
        return ApiResponse.<String>builder()
                .data(authService.login(userRequest))
                .success(true)
                .message("Login successful")
                .build();
    }
}