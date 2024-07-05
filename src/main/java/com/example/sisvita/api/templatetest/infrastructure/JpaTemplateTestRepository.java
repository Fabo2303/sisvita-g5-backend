package com.example.sisvita.api.templatetest.infrastructure;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTemplateTestRepository extends JpaRepository<TemplateTest, Integer> {
    @Query("SELECT t.name FROM TemplateTest t")
    List<String> findAllName();

    @Query("SELECT c.interpretation FROM Classification c JOIN TemplateTest t ON c.templateTest.id = t.id WHERE t.name = :name")
    List<String> findClassificationNameByName(String name);

    @Query("SELECT new com.example.sisvita.api.templatetest.dto.TemplateTestResponse(t.id, t.name) FROM TemplateTest t")
    List<TemplateTestResponse> findAllTemplateTestResponse();
}
