package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.PatientDTO;
import com.grupo5.sisvita.api.entities.Patient;
import com.grupo5.sisvita.api.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Patient>> getAllPatients(){
        Iterable<Patient> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<PatientDTO>> getAllPatientsDTO(){
        Iterable<PatientDTO> patients = patientService.findAllPatients();
        return ResponseEntity.ok(patients);
    }
}
