package com.rige.utils;

import com.rige.dtos.http.response.AssignmentDetailResponse;
import com.rige.dtos.response.*;
import com.rige.projections.CourseAssignmentProjection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssignmentMapper {
    public static CareerResponse mapToCareerResponse(List<AssignmentDetailResponse> flatList) {
        CareerResponse response = new CareerResponse();
        if (flatList == null || flatList.isEmpty()) return response;

        response.setCareer(flatList.get(0).getCareer());

        Map<String, Map<String, CourseResponse>> cycleCourseMap = new LinkedHashMap<>();

        for (AssignmentDetailResponse dto : flatList) {
            Map<String, CourseResponse> courseMap =
                    cycleCourseMap.computeIfAbsent(dto.getCycle(), k -> new LinkedHashMap<>());

            CourseResponse course = courseMap.get(dto.getCourseName());

            if (course == null) {
                course = new CourseResponse();
                course.setCourseId(dto.getCourseId());
                course.setCourse(dto.getCourseName());
                course.setCredits(dto.getCredits());
                course.setDetails(new ArrayList<>());
                courseMap.put(dto.getCourseName(), course);
            }

            CourseDetailResponse existingDetail = course.getDetails().stream()
                    .filter(d -> d.getTeacher().equals(dto.getProfessorName()))
                    .findFirst()
                    .orElse(null);

            if (existingDetail == null) {
                existingDetail = new CourseDetailResponse();
                existingDetail.setTeacher(dto.getProfessorName());
                existingDetail.setClassTypes(new ArrayList<>());
                course.getDetails().add(existingDetail);
            }

            ClassTypeResponse classType = new ClassTypeResponse();
            classType.setAssignmentDetailId(dto.getAssignmentDetailId());
            classType.setClassroom(dto.getClassroomName());
            classType.setClassType(dto.getClassType());
            classType.setDay(dto.getDay());
            classType.setStartTime(dto.getStartTime());
            classType.setEndTime(dto.getEndTime());

            existingDetail.getClassTypes().add(classType);
        }

        List<CycleResponse> cycles = cycleCourseMap.entrySet().stream()
                .map(entry -> {
                    CycleResponse cycle = new CycleResponse();
                    cycle.setCycle(entry.getKey());
                    cycle.setCourses(new ArrayList<>(entry.getValue().values()));
                    return cycle;
                })
                .collect(Collectors.toList());

        response.setCycles(cycles);
        return response;
    }
}
