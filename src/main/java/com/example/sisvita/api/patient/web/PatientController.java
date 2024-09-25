package com.example.sisvita.api.patient.web;

import com.example.sisvita.api.patient.domain.Patient;
import com.example.sisvita.api.patient.domain.PatientService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_PATIENT)
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody Patient patient){
        Boolean savedPatient = patientService.savePatient(patient);
        if (!savedPatient) {
            throw new CustomException("Patient not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient saved");
    }

    @GetMapping()
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) {
            throw new CustomException("No patients found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/id-user/{idUser}")
    public ResponseEntity<?> getIdByIdUser(@PathVariable Integer idUser) {
        Integer id = patientService.findIdByIdUser(idUser);
        if (id == null) {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
