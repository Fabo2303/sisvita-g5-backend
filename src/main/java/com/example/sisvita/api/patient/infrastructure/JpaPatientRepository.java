package com.example.sisvita.api.patient.infrastructure;

import com.example.sisvita.api.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaPatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p.id FROM Patient p WHERE p.user.id = :id_user")
    Integer findIdByIdUser(@Param("id_user") Integer idUser);
}
