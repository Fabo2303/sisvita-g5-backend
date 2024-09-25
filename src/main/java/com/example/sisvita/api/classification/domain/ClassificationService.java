package com.example.sisvita.api.classification.domain;

import com.example.sisvita.api.classification.infrastructure.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {
    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public Boolean saveClassification(Classification classification) {
        return classificationRepository.save(classification);
    }

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    public Classification findById(Integer id) {
        return classificationRepository.findById(id);
    }

    public Classification findByTemplateTestIdAndResult(Integer templateTestId, int result) {
        return classificationRepository.findByTemplateTestIdAndResult(templateTestId, result);
    }

    public List<Classification> findByTemplateTestName(String templateTestName) {
        return classificationRepository.findByTemplateTestName(templateTestName);
    }
}
