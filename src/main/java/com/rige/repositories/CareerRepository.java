package com.rige.repositories;

import com.rige.entities.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
}
