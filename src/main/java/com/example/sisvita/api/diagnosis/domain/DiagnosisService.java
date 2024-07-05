package com.example.sisvita.api.diagnosis.domain;

import com.example.sisvita.api.diagnosis.infrastructure.JpaDiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    private final JpaDiagnosisRepository jpaDiagnosisRepository;

    @Autowired
    public DiagnosisService(JpaDiagnosisRepository jpaDiagnosisRepository) {
        this.jpaDiagnosisRepository = jpaDiagnosisRepository;
    }

    public Diagnosis save(Diagnosis diagnosis) {
        return jpaDiagnosisRepository.save(diagnosis);
    }

    public List<Diagnosis> saveAll(List<Diagnosis> diagnoses) {
        return jpaDiagnosisRepository.saveAll(diagnoses);
    }

    public List<Diagnosis> findAll() {
        return jpaDiagnosisRepository.findAll();
    }

    public Diagnosis findById(Integer id) {
        return jpaDiagnosisRepository.findById(id).orElse(null);
    }
}
