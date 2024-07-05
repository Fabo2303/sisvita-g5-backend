package com.example.sisvita.api.consignation.infrastructure;

import com.example.sisvita.api.consignation.domain.Consignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface JpaConsignationRepository extends JpaRepository<Consignation, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consignation (date, observation, fundament, id_patient, id_specialist, id_resolved_test, id_diagnosis, id_treatment) " +
            "VALUES (:date, :observation, :fundament, :idPatient, :idSpecialist, :idResolvedTest, :idDiagnosis, :idTreatment)", nativeQuery = true)
    void insertConsignation(@Param("date") Timestamp date,
                            @Param("observation") String observation,
                            @Param("fundament") String fundament,
                            @Param("idPatient") Integer idPatient,
                            @Param("idSpecialist") Integer idSpecialist,
                            @Param("idResolvedTest") Integer idResolvedTest,
                            @Param("idDiagnosis") Integer idDiagnosis,
                            @Param("idTreatment") Integer idTreatment);

    @Query(value = "SELECT * FROM consignation WHERE id_patient = :idPatient AND id_resolved_test = :idResolvedTest ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Consignation findLastConsignation(@Param("idPatient") Integer idPatient, @Param("idResolvedTest") Integer idResolvedTest);
}
