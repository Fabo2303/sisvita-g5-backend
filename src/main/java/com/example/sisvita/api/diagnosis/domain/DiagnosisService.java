package com.example.sisvita.api.diagnosis.domain;

import com.example.sisvita.api.diagnosis.infrastructure.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Boolean save(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public Boolean saveAll(List<Diagnosis> diagnoses) {
        return diagnosisRepository.saveAll(diagnoses);
    }

    public List<Diagnosis> findAll() {
        return diagnosisRepository.findAll();
    }

    public Diagnosis findById(Integer id) {
        return diagnosisRepository.findById(id);
    }
}
