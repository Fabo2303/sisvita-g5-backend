package com.example.sisvita.api.templatetest.domain;

import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import com.example.sisvita.api.templatetest.infrastructure.TemplateTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateTestService {
    private final TemplateTestRepository templateTestRepository;

    public TemplateTestService(TemplateTestRepository templateTestRepository) {
        this.templateTestRepository = templateTestRepository;
    }

    public List<String> findAllName() {
        return templateTestRepository.findAllName();
    }

    public List<String> findClassificationNameByName(String name) {
        return templateTestRepository.findClassificationNameByName(name);
    }

    public Boolean saveTemplateTest(TemplateTest templateTest) {
        return templateTestRepository.save(templateTest);
    }

    public TemplateTest findById(Integer id) {
        return templateTestRepository.findById(id);
    }

    public List<TemplateTest> findAll() {
        return templateTestRepository.findAll();
    }

    public List<TemplateTestResponse> findAllTemplateTestResponse() {
        return templateTestRepository.findAllTemplateTestResponse();
    }
}
