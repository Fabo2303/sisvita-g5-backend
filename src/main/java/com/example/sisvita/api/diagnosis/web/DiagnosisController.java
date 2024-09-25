package com.example.sisvita.api.diagnosis.web;

import com.example.sisvita.api.diagnosis.domain.Diagnosis;
import com.example.sisvita.api.diagnosis.domain.DiagnosisService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_DIAGNOSIS)
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody Diagnosis diagnosis) {
        Boolean savedDiagnosis = diagnosisService.save(diagnosis);
        if (!savedDiagnosis) {
            throw new CustomException("Diagnosis not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Diagnosis saved");
    }

    @PostMapping("/all")
    public ResponseEntity<String> saveAll(@RequestBody List<Diagnosis> diagnoses) {
        Boolean savedDiagnoses = diagnosisService.saveAll(diagnoses);
        if (!savedDiagnoses) {
            throw new CustomException("Diagnoses not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Diagnoses saved");
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Diagnosis> diagnoses = diagnosisService.findAll();
        if (diagnoses.isEmpty()) {
            throw new CustomException("No diagnoses found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Diagnosis diagnosis = diagnosisService.findById(id);
        if (diagnosis == null) {
            throw new CustomException("Diagnosis not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(diagnosis);
    }
}
