package com.example.sisvita.api.classification.domain;

import com.example.sisvita.api.classification.infrastructure.JpaClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {
    private final JpaClassificationRepository classificationRepository;

    @Autowired
    public ClassificationService(JpaClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public Classification saveClassification(Classification classification) {
        return classificationRepository.save(classification);
    }

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    public Classification findById(Integer id) {
        return classificationRepository.findById(id).orElse(null);
    }

    public Classification findByTemplateTestIdAndResult(Integer templateTestId, int result) {
        return classificationRepository.findByTemplateTestIdAndResult(templateTestId, result).orElse(null);
    }

    public List<Classification> findByTemplateTestName(String templateTestName) {
        return classificationRepository.findByTemplateTestName(templateTestName);
    }
}
