package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.ClassificationDTO;
import com.grupo5.sisvita.api.entities.Classification;
import com.grupo5.sisvita.api.repositories.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassificationService {
    @Autowired
    private ClassificationRepository classificationRepository;

    public Classification saveClassification(Classification classification) {
        return classificationRepository.save(classification);
    }

    public Classification findById(Long id) {
        return classificationRepository.findById(id).orElse(null);
    }

    public List<ClassificationDTO> findAllClassifications() {
        List<Classification> classifications = classificationRepository.findAll();
        return classifications.stream().map(ClassificationDTO::fromEntity).collect(Collectors.toList());
    }

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    public Classification findByTemplateTestIdAndResult(Long templateTestId, int result) {
        Optional<Classification> classification = classificationRepository.findByTemplateTestIdAndResult(templateTestId, result);
        return classification.orElse(null);
    }

}
