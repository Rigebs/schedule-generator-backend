package com.rige.repositories;

import com.rige.entities.AssignmentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentDetailRepository extends JpaRepository<AssignmentDetailEntity, Long> {
}
