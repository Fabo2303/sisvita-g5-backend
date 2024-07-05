package com.example.sisvita.api.templatetest.web;

import com.example.sisvita.api.alternative.domain.Alternative;
import com.example.sisvita.api.classification.domain.Classification;
import com.example.sisvita.api.question.domain.Question;
import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.example.sisvita.api.templatetest.domain.TemplateTestService;
import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import com.example.sisvita.api.templatetest.dto.TemplateTestWithAlternativesAndQuestionsResponse;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/template-test")
public class TemplateTestController {
    private final TemplateTestService templateTestService;

    @Autowired
    public TemplateTestController(TemplateTestService templateTestService) {
        this.templateTestService = templateTestService;
    }

    @PostMapping()
    public ResponseEntity<?> createTemplateTest(@RequestBody TemplateTest templateTest) {
        for (Alternative alternative : templateTest.getAlternatives()) {
            alternative.setTemplateTest(templateTest);
        }
        for (Question question : templateTest.getQuestions()) {
            question.setTemplateTest(templateTest);
        }
        for (Classification classification : templateTest.getClassifications()) {
            classification.setTemplateTest(templateTest);
        }
        TemplateTest savedTemplateTest = templateTestService.saveTemplateTest(templateTest);
        return ResponseEntity.ok(savedTemplateTest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateTestById(@PathVariable Integer id) {
        TemplateTest templateTest = templateTestService.findById(id);
        if (templateTest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("TemplateTest not found")
                    .build());
        }
        return ResponseEntity.ok(templateTest);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<?> getTemplateTestDTOById(@PathVariable Integer id) {
        TemplateTestWithAlternativesAndQuestionsResponse templateTest = templateTestService.findDtoById(id);
        if (templateTest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("TemplateTest not found")
                    .build());
        }
        return ResponseEntity.ok(templateTest);
    }

    @GetMapping()
    public ResponseEntity<?> getAllTemplateTests() {
        List<TemplateTest> templateTests = templateTestService.findAll();
        if (templateTests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("TemplateTests not found")
                    .build());
        }
        return ResponseEntity.ok(templateTests);
    }

    @GetMapping("/dto")
    public ResponseEntity<?> getAllTemplateTestsDTO() {
        List<TemplateTestResponse> templateTestResponses = templateTestService.findAllTemplateTestResponse();
        if (templateTestResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("TemplateTests not found")
                    .build());
        }
        return ResponseEntity.ok(templateTestResponses);
    }


    @GetMapping("/name")
    public ResponseEntity<?> getAllTemplateTestsName() {
        List<String> templateTests = templateTestService.findAllName();
        if (templateTests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("TemplateTests not found")
                    .build());
        }
        return ResponseEntity.ok(templateTests);
    }

    @GetMapping("/classification")
    public ResponseEntity<?> getClassificationNameByName(@RequestParam String name) {
        List<String> classifications = templateTestService.findClassificationNameByName(name);
        if (classifications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Classifications not found")
                    .build());
        }
        return ResponseEntity.ok(classifications);
    }
}
