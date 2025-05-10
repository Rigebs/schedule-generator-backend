package com.rige.projections;

import java.sql.Time;

public interface CourseAssignmentProjection {
    Long getAssignmentDetailId();
    String getCareer();
    String getCycle();
    String getClassType();
    String getCourseId();
    String getCourseName();
    Integer getCredits();
    String getProfessorName();
    String getClassroomName();
    String getDay();
    Time getStartTime();
    Time getEndTime();
}

