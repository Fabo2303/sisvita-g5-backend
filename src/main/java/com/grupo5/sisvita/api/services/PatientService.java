package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.PatientDTO;
import com.grupo5.sisvita.api.dto.requests.PatientRequest;
import com.grupo5.sisvita.api.dto.requests.UserRequest;
import com.grupo5.sisvita.api.entities.Patient;
import com.grupo5.sisvita.api.entities.User;
import com.grupo5.sisvita.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    public Patient savePatient(PatientRequest patientRequest) {
        UserRequest userRequest = patientRequest.getUserRequest();
        Patient patient = new Patient();
        if (userRequest != null) {
            User user = userService.saveUser(userRequest);
            patient.setUser(user);
        }
        return patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    public List<PatientDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
