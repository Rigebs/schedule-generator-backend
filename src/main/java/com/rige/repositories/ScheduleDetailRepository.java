package com.rige.repositories;

import com.rige.entities.ScheduleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetailEntity, Long> {
}