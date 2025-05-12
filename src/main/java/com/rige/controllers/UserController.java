package com.rige.controllers;


import com.rige.dtos.request.LoginRequest;
import com.rige.dtos.request.RegisterRequest;
import com.rige.dtos.response.ApiResponse;
import com.rige.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/private/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping
    public ApiResponse<String> register(@RequestBody RegisterRequest registerRequest) {
        return ApiResponse.<String>builder()
                .data(authService.register(registerRequest))
                .success(true)
                .message("Register successful")
                .build();
    }
}
