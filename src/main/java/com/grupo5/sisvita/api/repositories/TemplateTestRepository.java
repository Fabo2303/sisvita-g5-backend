package com.grupo5.sisvita.api.repositories;

import com.grupo5.sisvita.api.entities.TemplateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemplateTestRepository extends JpaRepository<TemplateTest, Long> {

    @Query("SELECT t.name FROM TemplateTest t")
    List<String> findAllName();

    @Query("SELECT c.interpretation FROM Classification c JOIN TemplateTest t ON c.templateTest.id = t.id WHERE t.name = :name")
    List<String> findClassificationNameByName(String name);
}
