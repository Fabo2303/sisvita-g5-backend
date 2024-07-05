package com.example.sisvita.api.resolvedtest.web;

import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.example.sisvita.api.resolvedtest.domain.ResolvedTestService;
import com.example.sisvita.api.resolvedtest.dto.request.ResolvedTestRequest;
import com.example.sisvita.api.resolvedtest.dto.response.*;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resolved-test")
public class ResolvedTestController {
    private final ResolvedTestService resolvedTestService;

    @Autowired
    public ResolvedTestController(ResolvedTestService resolvedTestService) {
        this.resolvedTestService = resolvedTestService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> saveResolvedTest(@RequestBody ResolvedTestRequest resolvedTestRequest) {
        Map<String, ?> message = resolvedTestService.saveResolvedTest(resolvedTestRequest);
        return ResponseEntity.ok(message);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<ResolvedTest> resolvedTests = resolvedTestService.findAll();
        if (resolvedTests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("No resolved tests found").build());
        }
        return ResponseEntity.ok(resolvedTests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ResolvedTest resolvedTest = resolvedTestService.findById(id);
        if (resolvedTest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Resolved test not found").build());
        }
        return ResponseEntity.ok(resolvedTest);
    }

    @GetMapping("/response")
    public ResponseEntity<?> getAllResolvedTestsResponses() {
        List<ResolvedTestResponseTableFormat> resolvedTestsResponse = resolvedTestService.findAllResolvedTestResponse();
        return ResponseEntity.ok(resolvedTestsResponse);
    }

    @GetMapping("/findById/dto/{id}")
    public ResponseEntity<ResolvedTestReponseDataPatient> findByPatientId(@PathVariable Integer id) {
        ResolvedTestReponseDataPatient resolvedTestReponseDataPatient = resolvedTestService.findByIdAndReturnDataPatient(id);
        return ResponseEntity.ok().body(resolvedTestReponseDataPatient);
    }

    @GetMapping("/findByIdPatient/{id}")
    public ResponseEntity<?> findByPatientId2(@PathVariable Integer id) {
        List<ResolvedTestResponseViewPatient> resolvedTestsViewPatients = resolvedTestService.findByPatientId(id);
        if (resolvedTestsViewPatients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("No resolved tests found").build());
        }
        return ResponseEntity.ok(resolvedTestsViewPatients);
    }

    @GetMapping("/findByTemplateTestName")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByTemplateTestName(@RequestParam String name) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByTemplateTestName(name);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByClassificationInterpretation")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByClassificationInterpretation(@RequestParam String interpretation) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByClassificationInterpretation(interpretation);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByTemplateTestNameAndClassificationInterpretation")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByTemplateTestNameAndClassificationInterpretation(@RequestParam String name, @RequestParam String interpretation) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByTemplateTestNameAndClassificationInterpretation(name, interpretation);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByDateBetween")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByDateBetween(@RequestParam Date fechaInicio, @RequestParam Date fechaFin) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByDateBetween(fechaInicio, fechaFin);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByDateBetweenAndTemplateTestName")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByDateBetweenAndTemplateTestName(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String name) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByDateBetweenAndTemplateTestName(fechaInicio, fechaFin, name);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByDateBetweenAndClassificationInterpretation")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByDateBetweenAndClassificationInterpretation(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String interpretation) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByDateBetweenAndClassificationInterpretation(fechaInicio, fechaFin, interpretation);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/findByDateBetweenAndTemplateTestNameAndClassificationInterpretation")
    public ResponseEntity<List<ResolvedTestResponseTableFormat>> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String name, @RequestParam String interpretation) {
        List<ResolvedTestResponseTableFormat> resolvedTests = resolvedTestService.findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicio, fechaFin, name, interpretation);
        return ResponseEntity.ok().body(resolvedTests);
    }

    @GetMapping("/heat-map")
    public ResponseEntity<?> getHeatMap() {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findAllHeatMap();
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByTemplateTestNameHeatMap")
    public ResponseEntity<?> findByTemplateTestNameHeatMap(@RequestParam String name) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByTemplateTestNameHeatMap(name);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByClassificationInterpretationHeatMap")
    public ResponseEntity<?> findByClassificationInterpretationHeatMap(@RequestParam String interpretation) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByClassificationInterpretationHeatMap(interpretation);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByTemplateTestNameAndClassificationInterpretationHeatMap")
    public ResponseEntity<?> findByTemplateTestNameAndClassificationInterpretationHeatMap(@RequestParam String name, @RequestParam String interpretation) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByTemplateTestNameAndClassificationInterpretationHeatMap(name, interpretation);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByDateBetweenHeatMap")
    public ResponseEntity<?> findByDateBetweenHeatMap(@RequestParam Date fechaInicio, @RequestParam Date fechaFin) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByDateBetweenHeatMap(fechaInicio, fechaFin);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByDateBetweenAndTemplateTestNameHeatMap")
    public ResponseEntity<?> findByDateBetweenAndTemplateTestNameHeatMap(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String name) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByDateBetweenAndTemplateTestNameHeatMap(fechaInicio, fechaFin, name);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByDateBetweenAndClassificationInterpretationHeatMap")
    public ResponseEntity<?> findByDateBetweenAndClassificationInterpretationHeatMap(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String interpretation) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByDateBetweenAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, interpretation);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }

    @GetMapping("/findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap")
    public ResponseEntity<?> findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String name, @RequestParam String interpretation) {
        List<ResolvedTestResponseHeatMap> heatMap = resolvedTestService.findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, name, interpretation);
        List<ResolvedTestResponseHeatMapTotalIntensity> heatMapTotalIntensity = heatMap.stream().map(
                resolvedTestService::fromResolvedTestResponseHeatMap
        ).toList();
        return ResponseEntity.ok(heatMapTotalIntensity);
    }
}
