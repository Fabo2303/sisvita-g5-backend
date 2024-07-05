package com.example.sisvita.api.patient.domain;

import com.example.sisvita.api.patient.dto.PatientRequest;
import com.example.sisvita.api.patient.infrastructure.JpaPatientRepository;
import com.example.sisvita.api.user.domain.User;
import com.example.sisvita.api.user.domain.UserService;
import com.example.sisvita.api.user.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final JpaPatientRepository jpaPatientRepository;
    private final UserService userService;

    @Autowired
    public PatientService(
            JpaPatientRepository jpaPatientRepository,
            UserService userService
    ) {
        this.jpaPatientRepository = jpaPatientRepository;
        this.userService = userService;
    }

    public Integer findIdByIdUser(Integer idUser) {
        return jpaPatientRepository.findIdByIdUser(idUser);
    }

    public Patient savePatient(PatientRequest patientRequest) {
        UserRequest userRequest = patientRequest.getUserRequest();
        Patient patient = new Patient();
        if (userRequest != null) {
            User user = userService.saveUser(userRequest);
            patient.setUser(user);
        }
        return jpaPatientRepository.save(patient);
    }

    public Patient findById(Integer id) {
        return jpaPatientRepository.findById(id).orElse(null);
    }

    public List<Patient> findAll() {
        return jpaPatientRepository.findAll();
    }
}
