package com.rige.services;

import com.rige.dtos.request.LoginRequest;
import com.rige.dtos.request.RegisterRequest;

public interface AuthService {
    String login(LoginRequest userRequest);
    String register(RegisterRequest registerRequest);
}
