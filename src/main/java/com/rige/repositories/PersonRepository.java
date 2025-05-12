package com.rige.repositories;

import com.rige.entities.PersonEntity;
import com.rige.projections.CareerCreditsUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    @Query(value = "CALL sp_get_career_by_email_user(:email)", nativeQuery = true)
    CareerCreditsUserProjection getCareerIdByEmail(@Param("email") String email);
}
