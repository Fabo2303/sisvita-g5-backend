package com.grupo5.sisvita.api.repositories;

import com.grupo5.sisvita.api.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p.id FROM Patient p WHERE p.user.id = :user_id")
    Long findIdByIdUser(@Param("user_id") Long userId);
}
