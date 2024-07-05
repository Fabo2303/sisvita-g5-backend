package com.example.sisvita.api.classification.infrastructure;

import com.example.sisvita.api.classification.domain.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClassificationRepository extends JpaRepository<Classification, Integer> {
    @Query("SELECT c FROM Classification c WHERE c.templateTest.id = :idTemplateTest AND :result BETWEEN c.minimum AND c.maximum")
    Optional<Classification> findByTemplateTestIdAndResult(@Param("idTemplateTest") Integer idTemplateTest, @Param("result") int result);

    @Query("SELECT c FROM Classification c WHERE c.templateTest.name = :templateTestName")
    List<Classification> findByTemplateTestName(@Param("templateTestName") String templateTestName);
}