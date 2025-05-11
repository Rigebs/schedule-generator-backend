package com.rige.repositories;

import com.rige.entities.AssignmentDetailEntity;
import com.rige.projections.CourseAssignmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDetailRepository extends JpaRepository<AssignmentDetailEntity, Long> {
    @Query(value = "CALL sp_get_assignments_by_courses(:courseIds, :careerId)", nativeQuery = true)
    List<CourseAssignmentProjection> findAssignmentsByCourseIdList(
            @Param("courseIds") String courseIds,
            @Param("careerId") Long careerId);
}
