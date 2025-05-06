package com.rige.repositories;

import com.rige.entities.AssignmentEntity;
import com.rige.projections.CourseAssignmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    @Query(value = "CALL sp_get_assignments_by_career(:careerId)", nativeQuery = true)
    List<CourseAssignmentProjection> findAssignmentsByCareerId(@Param("careerId") Integer careerId);
}
