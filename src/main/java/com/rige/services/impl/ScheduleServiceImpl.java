package com.rige.services.impl;

import com.rige.dtos.http.response.AssignmentDetailResponse;
import com.rige.dtos.request.GenerateSchedulesRequest;
import com.rige.dtos.response.CareerResponse;
import com.rige.projections.CourseAssignmentProjection;
import com.rige.repositories.AssignmentDetailRepository;
import com.rige.services.ScheduleService;

import com.rige.utils.AssignmentMapper;
import com.rige.utils.ListFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final AssignmentDetailRepository assignmentDetailRepository;
    private final RestTemplate restTemplate;

    @Value("${custom.api-url}")
    private String apiUrl;

    @Override
    public CareerResponse generateSchedules(GenerateSchedulesRequest request) {
        List<CourseAssignmentProjection> assignments = assignmentDetailRepository.findAssignmentsByCourseIdList(
                ListFormatter.formatList(request.getCourseIds()),
                request.getCareerId());

        Map<String, Object> payload = new HashMap<>();
        payload.put("assignments", assignments);
        payload.put("preferences", request.getPreferences());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        String url = apiUrl + "/generate-schedule";

        ResponseEntity<List<AssignmentDetailResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<>() {}
        );

        return AssignmentMapper.mapToCareerResponse(response.getBody());
    }
}
