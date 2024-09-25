package com.example.sisvita.api.consignment.web;

import com.example.sisvita.api.consignment.domain.Consignment;
import com.example.sisvita.api.consignment.domain.ConsignmentService;
import com.example.sisvita.api.consignment.dto.ConsignmentRequest;
import com.example.sisvita.utilz.ErrorResponse;
import com.example.sisvita.utilz.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_CONSIGNMENT)
public class ConsignmentController {
    private final ConsignmentService consignmentService;

    @Autowired
    public ConsignmentController(ConsignmentService consignmentService) {
        this.consignmentService = consignmentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody ConsignmentRequest consignmentRequest) {
        consignmentService.insert(consignmentRequest);
        return ResponseEntity.ok("Consignacion guardada");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Consignment consignment = consignmentService.findById(id);
        if (consignment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Consignation not found")
                    .build());
        }
        return ResponseEntity.ok(consignment);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Consignment> consignments = consignmentService.findAll();
        if (consignments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Consignations not found")
                    .build());
        }
        return ResponseEntity.ok(consignments);
    }
}
