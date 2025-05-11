package com.rige.dtos.request;

import lombok.Data;

@Data
public class BlockedHourRequest {
    private String day;
    private String start;
    private String end;
}
