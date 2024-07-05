package com.example.sisvita.api.specialist.infrastructure;

import com.example.sisvita.api.specialist.domain.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaSpecialistRepository extends JpaRepository<Specialist, Integer> {
    @Query("SELECT p.id FROM Specialist p WHERE p.user.id = :id_user")
    Integer findIdByIdUser(@Param("id_user") Integer idUser);
}
