package com.example.sisvita.api.resolvedtest.infrastructure;

import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap;
import com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface JpaResolvedTestRepository extends JpaRepository<ResolvedTest, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO resolved_test (date, result, id_template_test, id_classification, id_patient) VALUES (:date, :result, :idTemplateTest, :idClassification, :idPatient)")
    void insert(@Param("date") Timestamp date, @Param("result") int result, @Param("idTemplateTest") Integer idTemplateTest, @Param("idClassification") Integer idClassification, @Param("idPatient") Integer idPatient);

    @Query(value = "SELECT * FROM resolved_test WHERE id_patient = :idPatient", nativeQuery = true)
    List<ResolvedTest> findByIdPatient(Integer idPatient);

    @Query(value = "SELECT * FROM resolved_test WHERE id_patient = :idPatient ORDER BY date DESC LIMIT 1", nativeQuery = true)
    ResolvedTest findTopByPacienteIdOrderByDateDesc(@Param("idPatient") Integer idPatient);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.templateTest.name = :name")
    List<ResolvedTestResponseTableFormat> findByTemplateTestName(@Param("name") String name);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.classification.interpretation = :interpretation")
    List<ResolvedTestResponseTableFormat> findByClassificationInterpretation(@Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.templateTest.name = :name AND rt.classification.interpretation = :interpretation")
    List<ResolvedTestResponseTableFormat> findByTemplateTestNameAndClassificationInterpretation(@Param("name") String name, @Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin")
    List<ResolvedTestResponseTableFormat> findByDateBetween(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name")
    List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestName(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.classification.interpretation = :interpretation")
    List<ResolvedTestResponseTableFormat> findByDateBetweenAndClassificationInterpretation(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name AND rt.classification.interpretation = :interpretation")
    List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name, @Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseTableFormat(rt.id, rt.patient.user.person.name, rt.patient.user.person.lastName, rt.date, rt.templateTest.name, rt.classification.anxietyColor.color) " + "FROM ResolvedTest rt")
    List<ResolvedTestResponseTableFormat> findAllResolvedTestsResponse();

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findAllResolvedTestHeatMap();

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByDateBetweenHeatMap(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameHeatMap(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.classification.interpretation = :interpretation " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByDateBetweenAndClassificationInterpretationHeatMap(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name AND rt.classification.interpretation = :interpretation " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name, @Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.templateTest.name = :name " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByTemplateTestNameHeatMap(@Param("name") String name);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.classification.interpretation = :interpretation " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByClassificationInterpretationHeatMap(@Param("interpretation") String interpretation);

    @Query("SELECT new com.example.sisvita.api.resolvedtest.dto.response.ResolvedTestResponseHeatMap(" +
            "MIN(rt.id), rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, " +
            "rt.classification.intensity, COUNT(rt.id)) " +
            "FROM ResolvedTest rt WHERE rt.templateTest.name = :name AND rt.classification.interpretation = :interpretation " +
            "GROUP BY rt.patient.user.person.ubigeo.ubigeo, rt.patient.user.person.ubigeo.department, " +
            "rt.patient.user.person.ubigeo.latitude, rt.patient.user.person.ubigeo.longitude, rt.classification.intensity")
    List<ResolvedTestResponseHeatMap> findByTemplateTestNameAndClassificationInterpretationHeatMap(@Param("name") String name, @Param("interpretation") String interpretation);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE resolved_test SET id_consignation = :idConsignation WHERE id = :idTestResolved")
    void updateConsignationId(Integer idConsignation, Integer idTestResolved);
}
