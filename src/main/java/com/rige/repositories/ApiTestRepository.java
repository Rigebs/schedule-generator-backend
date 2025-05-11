package com.rige.repositories;

import com.rige.entities.ApiTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTestRepository extends JpaRepository<ApiTestEntity, Long> {
}
