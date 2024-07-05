package com.example.sisvita.api.consignation.web;

import com.example.sisvita.api.consignation.domain.Consignation;
import com.example.sisvita.api.consignation.domain.ConsignationService;
import com.example.sisvita.api.consignation.dto.ConsignationRequest;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consignation")
public class ConsignationController {
    private final ConsignationService consignationService;

    @Autowired
    public ConsignationController(ConsignationService consignationService) {
        this.consignationService = consignationService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody ConsignationRequest consignationRequest) {
        consignationService.insert(consignationRequest);
        return ResponseEntity.ok("Consignacion guardada");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Consignation consignation = consignationService.findById(id);
        if (consignation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Consignation not found")
                    .build());
        }
        return ResponseEntity.ok(consignation);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Consignation> consignations = consignationService.findAll();
        if (consignations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Consignations not found")
                    .build());
        }
        return ResponseEntity.ok(consignations);
    }
}
