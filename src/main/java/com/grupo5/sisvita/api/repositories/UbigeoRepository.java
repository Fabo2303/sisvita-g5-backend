package com.grupo5.sisvita.api.repositories;

import com.grupo5.sisvita.api.entities.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UbigeoRepository extends JpaRepository<Ubigeo, String> {
    @Query("SELECT DISTINCT u.provincia FROM Ubigeo u WHERE u.departamento = :departamento")
    List<String> findProvinciasByDepartamento(@Param("departamento") String departamento);

    @Query("SELECT DISTINCT u.distrito FROM Ubigeo u WHERE u.departamento = :departamento AND u.provincia = :provincia")
    List<String> findDistritosByDepartamentoAndProvincia(@Param("departamento") String departamento, @Param("provincia") String provincia);

    @Query("SELECT u FROM Ubigeo u WHERE u.departamento = :departamento AND u.provincia = :provincia AND u.distrito = :distrito")
    Optional<Ubigeo> findUbigeoByDepartamentoAndProvinciaAndDistrito(@Param("departamento") String departamento, @Param("provincia") String provincia, @Param("distrito") String distrito);

    @Query("SELECT DISTINCT u.departamento FROM Ubigeo u")
    List<String> findAllDepartamentos();
}
