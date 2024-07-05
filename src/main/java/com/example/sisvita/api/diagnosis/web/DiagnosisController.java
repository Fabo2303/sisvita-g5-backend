package com.example.sisvita.api.diagnosis.web;

import com.example.sisvita.api.diagnosis.domain.Diagnosis;
import com.example.sisvita.api.diagnosis.domain.DiagnosisService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(diagnosisService.save(diagnosis));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveAll(@RequestBody List<Diagnosis> diagnoses) {
        return ResponseEntity.ok(diagnosisService.saveAll(diagnoses));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Diagnosis> diagnoses = diagnosisService.findAll();
        if (diagnoses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Diagnoses not found").build());
        }
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Diagnosis diagnosis = diagnosisService.findById(id);
        if (diagnosis == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Diagnosis not found").build());
        }
        return ResponseEntity.ok(diagnosis);
    }
}
