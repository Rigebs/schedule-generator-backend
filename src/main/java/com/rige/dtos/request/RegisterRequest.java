package com.rige.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Long personId;
}