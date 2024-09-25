package com.example.sisvita.api.patient.domain;

import com.example.sisvita.api.patient.infrastructure.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Integer findIdByIdUser(Integer idUser) {
        return patientRepository.findIdByIdUser(idUser);
    }

    public Boolean savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Integer id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
