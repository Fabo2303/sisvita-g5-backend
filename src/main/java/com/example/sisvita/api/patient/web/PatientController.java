package com.example.sisvita.api.patient.web;

import com.example.sisvita.api.patient.domain.Patient;
import com.example.sisvita.api.patient.domain.PatientService;
import com.example.sisvita.api.patient.dto.PatientRequest;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping()
    public ResponseEntity<?> createPatient(@RequestBody PatientRequest patientRequest){
        Patient patient = patientService.savePatient(patientRequest);
        Map<String, String> response = new HashMap<>();
        if (patient == null) {
            response.put("error", "Error al crear paciente");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Paciente creado con éxito");
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Patients not found")
                    .build());
        }
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Patient not found")
                    .build());
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/id-user/{idUser}")
    public ResponseEntity<?> getIdByIdUser(@PathVariable Integer idUser) {
        Integer id = patientService.findIdByIdUser(idUser);
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Patient not found")
                    .build());
        }
        return ResponseEntity.ok(id);
    }
}
