package com.example.sisvita.api.treatment.web;

import com.example.sisvita.api.treatment.domain.Treatment;
import com.example.sisvita.api.treatment.domain.TreatmentService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_TREATMENT)
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody Treatment treatment) {
        Boolean savedTreatment = treatmentService.save(treatment);
        if (!savedTreatment) {
            throw new CustomException("Treatment not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Treatment saved");
    }

    @PostMapping("/all")
    public ResponseEntity<String> saveAll(@RequestBody List<Treatment> treatments) {
        Boolean savedTreatments = treatmentService.saveAll(treatments);
        if (!savedTreatments) {
            throw new CustomException("Treatments not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Treatments saved");
    }

    @GetMapping()
    public ResponseEntity<List<Treatment>> findAll() {
        List<Treatment> treatments = treatmentService.findAll();
        if (treatments.isEmpty()) {
            throw new CustomException("No treatments found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(treatments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> findById(@PathVariable Integer id) {
        Treatment treatment = treatmentService.findById(id);
        if (treatment == null) {
            throw new CustomException("Treatment not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(treatment);
    }
}
