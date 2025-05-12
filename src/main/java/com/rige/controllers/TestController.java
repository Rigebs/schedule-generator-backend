package com.rige.controllers;

import com.rige.dtos.response.ApiResponse;
import com.rige.services.ApiTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final ApiTestService apiTestService;

    @GetMapping
    public ApiResponse<?> test() {
        return new ApiResponse<>(true, "API works!", apiTestService.test());
    }
}
