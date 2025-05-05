package com.rige.repositories;

import com.rige.entities.TimeslotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends JpaRepository<TimeslotEntity, Long> {
}