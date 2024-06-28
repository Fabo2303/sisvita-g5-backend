package com.grupo5.sisvita.api.repositories;

import com.grupo5.sisvita.api.entities.ResolvedTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ResolvedTestRepository extends JpaRepository<ResolvedTest, Long> {

    List<ResolvedTest> findByTemplateTestName(String name);

    List<ResolvedTest> findByClassificationInterpretation(String interpretation);

    List<ResolvedTest> findByTemplateTestNameAndClassificationInterpretation(String name, String interpretation);

    @Query("SELECT rt FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin")
    List<ResolvedTest> findByDateBetween(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    // date and name
    @Query("SELECT rt FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name")
    List<ResolvedTest> findByDateBetweenAndTemplateTestName(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name);

    // date and interpretation
    @Query("SELECT rt FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.classification.interpretation = :interpretation")
    List<ResolvedTest> findByDateBetweenAndClassificationInterpretation(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("interpretation") String interpretation);

    // date, name and interpretation
    @Query("SELECT rt FROM ResolvedTest rt WHERE rt.date BETWEEN :fechaInicio AND :fechaFin AND rt.templateTest.name = :name AND rt.classification.interpretation = :interpretation")
    List<ResolvedTest> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("name") String name, @Param("interpretation") String interpretation);
}
