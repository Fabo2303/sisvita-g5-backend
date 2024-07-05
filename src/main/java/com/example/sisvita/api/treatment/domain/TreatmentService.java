package com.example.sisvita.api.treatment.domain;

import com.example.sisvita.api.treatment.infrastructure.JpaTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    private final JpaTreatmentRepository jpaTreatmentRepository;

    @Autowired
    public TreatmentService(JpaTreatmentRepository jpaTreatmentRepository) {
        this.jpaTreatmentRepository = jpaTreatmentRepository;
    }

    public Treatment save(Treatment treatment) {
        return jpaTreatmentRepository.save(treatment);
    }

    public List<Treatment> saveAll(List<Treatment> treatments) {
        return jpaTreatmentRepository.saveAll(treatments);
    }

    public List<Treatment> findAll() {
        return jpaTreatmentRepository.findAll();
    }

    public Treatment findById(Integer id) {
        return jpaTreatmentRepository.findById(id).orElse(null);
    }
}
