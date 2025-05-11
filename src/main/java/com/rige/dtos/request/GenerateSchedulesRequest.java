package com.rige.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class GenerateSchedulesRequest {
    private Long careerId;
    private PreferencesRequest preferences;
    private List<Long> courseIds;
}
