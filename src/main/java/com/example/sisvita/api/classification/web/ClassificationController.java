package com.example.sisvita.api.classification.web;

import com.example.sisvita.api.classification.domain.Classification;
import com.example.sisvita.api.classification.domain.ClassificationService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classification")
public class ClassificationController {
    private final ClassificationService classificationService;

    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Classification> classifications = classificationService.findAll();
        if (classifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Classifications not found")
                    .build());
        }
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Classification classification = classificationService.findById(id);
        if (classification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Classification not found")
                    .build());
        }
        return ResponseEntity.ok().body(classification);
    }

    @GetMapping("/findByTemplateTestName")
    public ResponseEntity<?> findByTemplateTestName(@RequestParam String templateTestName) {
        List<Classification> classification = classificationService.findByTemplateTestName(templateTestName);
        if (classification.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Classification not found")
                    .build());
        }
        return ResponseEntity.ok().body(classification);
    }

    @GetMapping("/findByTemplateTestIdAndResult")
    public ResponseEntity<?> findByTemplateTestIdAndResult(@RequestParam Integer templateTestId, @RequestParam int result) {
        Classification classification = classificationService.findByTemplateTestIdAndResult(templateTestId, result);
        if (classification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Classification not found")
                    .build());
        }
        return ResponseEntity.ok().body(classification);
    }
}
