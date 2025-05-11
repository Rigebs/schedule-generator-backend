package com.rige.services.impl;

import com.rige.entities.ApiTestEntity;
import com.rige.repositories.ApiTestRepository;
import com.rige.services.ApiTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiTestServiceImpl implements ApiTestService {

    private final ApiTestRepository apiTestRepository;

    @Override
    public List<ApiTestEntity> test() {
        return apiTestRepository.findAll();
    }
}
