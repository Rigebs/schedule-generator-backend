package com.rige.repositories;

import com.rige.entities.PreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<PreferenceEntity, Long> {
}