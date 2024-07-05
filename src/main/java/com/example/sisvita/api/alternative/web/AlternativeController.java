package com.example.sisvita.api.alternative.web;

import com.example.sisvita.api.alternative.domain.Alternative;
import com.example.sisvita.api.alternative.domain.AlternativeService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternative")
public class AlternativeController {
    private final AlternativeService alternativeService;

    @Autowired
    public AlternativeController(AlternativeService alternativeService) {
        this.alternativeService = alternativeService;
    }

    @PostMapping()
    public ResponseEntity<?> saveAlternative(@RequestBody Alternative alternative) {
        Alternative savedAlternative = alternativeService.save(alternative);
        return ResponseEntity.ok(savedAlternative);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Alternative> alternatives = alternativeService.findAll();
        if (alternatives.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Alternatives not found")
                    .build());
        }
        return ResponseEntity.ok(alternatives);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Alternative alternative = alternativeService.findById(id);
        if (alternative == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Alternative not found")
                    .build());
        }
        return ResponseEntity.ok().body(alternative);
    }
}
