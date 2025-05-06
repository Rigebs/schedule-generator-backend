package com.rige.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class CareerResponse {
    private String career;
    private List<CycleResponse> cycles;
}

