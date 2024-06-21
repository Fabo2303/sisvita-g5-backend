package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.ResolvedTestDTO;
import com.grupo5.sisvita.api.entities.Classification;
import com.grupo5.sisvita.api.entities.ResolvedTest;
import com.grupo5.sisvita.api.entities.TemplateTest;
import com.grupo5.sisvita.api.services.ClassificationService;
import com.grupo5.sisvita.api.services.ResolvedTestService;
import com.grupo5.sisvita.api.services.TemplateTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/resolved-test")
public class ResolvedTestController {

    @Autowired
    private ResolvedTestService resolvedTestService;

    @Autowired
    private TemplateTestService templateTestService;

    @Autowired
    private ClassificationService classificationService;

    @PostMapping()
    public ResponseEntity<ResolvedTest> createResolvedTest(@RequestBody ResolvedTestDTO resolvedTestDTO) {
        TemplateTest templateTest = templateTestService.findById(resolvedTestDTO.getTemplateTestId());
        ResolvedTest resolvedTest = resolvedTestDTO.toEntity();
        resolvedTest.setTemplateTest(templateTest);
        resolvedTest.setResult(resolvedTestService.sumResultFromAlternatives(resolvedTest));
        Classification classification = classificationService.findByTemplateTestIdAndResult(templateTest.getId(), resolvedTest.getResult());
        resolvedTest.setClassification(classification);
        ResolvedTest savedResolvedTest = resolvedTestService.saveResolvedTest(resolvedTest);
        return ResponseEntity.ok(savedResolvedTest);
    }

    @GetMapping()
    public ResponseEntity<Iterable<ResolvedTest>> findAll() {
        Iterable<ResolvedTest> resolvedTests = resolvedTestService.findAll();
        return ResponseEntity.ok(resolvedTests);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<ResolvedTestDTO>> getAllResolvedTests() {
        Iterable<ResolvedTestDTO> resolvedTests = resolvedTestService.findAllResolvedTests();
        return ResponseEntity.ok(resolvedTests);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResolvedTest> findById(@PathVariable Long id) {
        ResolvedTest resolvedTest = resolvedTestService.findById(id);
        return ResponseEntity.ok().body(resolvedTest);
    }
}
