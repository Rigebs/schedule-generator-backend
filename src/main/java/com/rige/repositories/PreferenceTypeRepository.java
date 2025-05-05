package com.rige.repositories;

import com.rige.entities.PreferenceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceTypeRepository extends JpaRepository<PreferenceTypeEntity, Long> {
}