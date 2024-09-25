package com.example.sisvita.api.templatetest.web;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.example.sisvita.api.templatetest.domain.TemplateTestService;
import com.example.sisvita.api.templatetest.dto.TemplateTestResponse;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_TEMPLATE_TEST)
public class TemplateTestController {
    private final TemplateTestService templateTestService;

    public TemplateTestController(TemplateTestService templateTestService) {
        this.templateTestService = templateTestService;
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody TemplateTest templateTest) {
        Boolean savedTemplateTest = templateTestService.saveTemplateTest(templateTest);
        if (!savedTemplateTest) {
            throw new CustomException("TemplateTest not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("TemplateTest saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemplateTest> getTemplateTestById(@PathVariable Integer id) {
        TemplateTest templateTest = templateTestService.findById(id);
        if (templateTest == null) {
            throw new CustomException("TemplateTest not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(templateTest);
    }

    @GetMapping()
    public ResponseEntity<List<TemplateTest>> getAllTemplateTests() {
        List<TemplateTest> templateTests = templateTestService.findAll();
        if (templateTests.isEmpty()) {
            throw new CustomException("TemplateTests not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(templateTests);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<TemplateTestResponse>> getAllTemplateTestsDTO() {
        List<TemplateTestResponse> templateTestResponses = templateTestService.findAllTemplateTestResponse();
        if (templateTestResponses.isEmpty()) {
            throw new CustomException("TemplateTests not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(templateTestResponses);
    }


    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllTemplateTestsName() {
        List<String> templateTests = templateTestService.findAllName();
        if (templateTests.isEmpty()) {
            throw new CustomException("TemplateTests not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(templateTests);
    }

    @GetMapping("/classification")
    public ResponseEntity<List<String>> getClassificationNameByName(@RequestParam String name) {
        List<String> classifications = templateTestService.findClassificationNameByName(name);
        if (classifications.isEmpty()) {
            throw new CustomException("Classifications not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(classifications);
    }
}
