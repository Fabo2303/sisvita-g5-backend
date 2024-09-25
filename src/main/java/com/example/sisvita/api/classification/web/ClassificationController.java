package com.example.sisvita.api.classification.web;

import com.example.sisvita.api.classification.domain.Classification;
import com.example.sisvita.api.classification.domain.ClassificationService;
import com.example.sisvita.utilz.CustomException;
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
    public ResponseEntity<List<Classification>> findAll() {
        List<Classification> classifications = classificationService.findAll();
        if (classifications.isEmpty()) {
            throw new CustomException("Classifications not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classification> findById(@PathVariable Integer id) {
        Classification classification = classificationService.findById(id);
        if (classification == null) {
            throw new CustomException("Classification not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(classification);
    }

    @GetMapping("/findByTemplateTestName")
    public ResponseEntity<List<Classification>> findByTemplateTestName(@RequestParam String templateTestName) {
        List<Classification> classification = classificationService.findByTemplateTestName(templateTestName);
        if (classification.isEmpty()) {
            throw new CustomException("Classifications not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(classification);
    }

    @GetMapping("/findByTemplateTestIdAndResult")
    public ResponseEntity<Classification> findByTemplateTestIdAndResult(@RequestParam Integer templateTestId, @RequestParam int result) {
        Classification classification = classificationService.findByTemplateTestIdAndResult(templateTestId, result);
        if (classification == null) {
            throw new CustomException("Classification not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(classification);
    }
}
