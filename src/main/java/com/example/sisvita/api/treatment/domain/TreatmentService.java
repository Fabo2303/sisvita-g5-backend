package com.example.sisvita.api.treatment.domain;

import com.example.sisvita.api.treatment.infrastructure.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public Boolean save(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Boolean saveAll(List<Treatment> treatments) {
        return treatmentRepository.saveAll(treatments);
    }

    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    public Treatment findById(Integer id) {
        return treatmentRepository.findById(id);
    }
}
