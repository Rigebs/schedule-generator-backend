package com.rige.projections;

import java.sql.Time;

public interface CourseAssignmentProjection {
    Long getAssignmentId();
    String getCareer();
    String getCycle();
    String getCourseId();
    String getCourseName();
    Integer getCredits();
    String getProfessorName();
    String getClassroomName();
    String getDay();
    Time getStartTime();
    Time getEndTime();
}

