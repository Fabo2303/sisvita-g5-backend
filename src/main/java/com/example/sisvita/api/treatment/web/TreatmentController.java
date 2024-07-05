package com.example.sisvita.api.treatment.web;

import com.example.sisvita.api.treatment.domain.Treatment;
import com.example.sisvita.api.treatment.domain.TreatmentService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {
    private final TreatmentService treatmentService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Treatment treatment) {
        Treatment savedTreatment = treatmentService.save(treatment);
        return ResponseEntity.ok(savedTreatment);
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveAll(@RequestBody List<Treatment> treatments) {
        List<Treatment> savedTreatments = treatmentService.saveAll(treatments);
        return ResponseEntity.ok(savedTreatments);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Treatment> treatments = treatmentService.findAll();
        if (treatments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Treatments not found").build());
        }
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Treatment treatment = treatmentService.findById(id);
        if (treatment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Treatment not found").build());
        }
        return ResponseEntity.ok(treatment);
    }
}
