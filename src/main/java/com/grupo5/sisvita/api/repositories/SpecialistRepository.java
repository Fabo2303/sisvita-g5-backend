package com.grupo5.sisvita.api.repositories;

import com.grupo5.sisvita.api.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {

    @Query("SELECT s.id FROM Specialist s WHERE s.user.id = :user_id")
    Long findIdByIdUser(@Param("user_id") Long userId);
}
