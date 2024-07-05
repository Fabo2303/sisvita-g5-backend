package com.example.sisvita.api.templatetest.domain;

import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import com.example.sisvita.api.templatetest.dto.TemplateTestWithAlternativesAndQuestionsResponse;
import com.example.sisvita.api.templatetest.infrastructure.JpaTemplateTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateTestService {
    private final JpaTemplateTestRepository jpaTemplateTestRepository;

    @Autowired
    public TemplateTestService(JpaTemplateTestRepository jpaTemplateTestRepository) {
        this.jpaTemplateTestRepository = jpaTemplateTestRepository;
    }

    public List<String> findAllName() {
        return jpaTemplateTestRepository.findAllName();
    }

    public List<String> findClassificationNameByName(String name) {
        return jpaTemplateTestRepository.findClassificationNameByName(name);
    }

    public TemplateTest saveTemplateTest(TemplateTest templateTest) {
        return jpaTemplateTestRepository.save(templateTest);
    }

    public TemplateTest findById(Integer id) {
        return jpaTemplateTestRepository.findById(id).orElse(null);
    }

    public List<TemplateTest> findAll() {
        return jpaTemplateTestRepository.findAll();
    }

    public List<TemplateTestResponse> findAllTemplateTestResponse() {
        return jpaTemplateTestRepository.findAllTemplateTestResponse();
    }

    public TemplateTestWithAlternativesAndQuestionsResponse findDtoById(Integer id) {
        TemplateTest templateTest = jpaTemplateTestRepository.findById(id).orElse(null);
       if (templateTest == null) {
            return null;
        }
        return TemplateTestWithAlternativesAndQuestionsResponse.fromEntity(templateTest);
    }
}
