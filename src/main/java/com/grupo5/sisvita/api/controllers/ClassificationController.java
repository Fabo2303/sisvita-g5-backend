package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.ClassificationDTO;
import com.grupo5.sisvita.api.entities.Classification;
import com.grupo5.sisvita.api.services.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/classification")
public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;

    @GetMapping()
    public ResponseEntity<Iterable<Classification>> findAll() {
        Iterable<Classification> classifications = classificationService.findAll();
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<ClassificationDTO>> getAllClassifications() {
        Iterable<ClassificationDTO> classifications = classificationService.findAllClassifications();
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Classification> findById(Long id) {
        Classification classification = classificationService.findById(id);
        return ResponseEntity.ok().body(classification);
    }
}
