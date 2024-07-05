package com.example.sisvita.api.anxietycolor.web;

import com.example.sisvita.api.anxietycolor.domain.AnxietyColor;
import com.example.sisvita.api.anxietycolor.domain.AnxietyColorService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anxiety-color")
public class AnxietyColorController {
    private final AnxietyColorService anxietyColorService;

    @Autowired
    public AnxietyColorController(AnxietyColorService anxietyColorService) {
        this.anxietyColorService = anxietyColorService;
    }

    @PostMapping()
    public ResponseEntity<?> createAnxietyColor(@RequestBody AnxietyColor anxietyColor) {
        AnxietyColor savedAnxietyColor = anxietyColorService.save(anxietyColor);
        return ResponseEntity.ok(savedAnxietyColor);
    }

    @GetMapping()
    public ResponseEntity<?> getAllAnxietyColors() {
        List<AnxietyColor> anxietyColors = anxietyColorService.findAll();
        if (anxietyColors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Anxiety colors not found")
                    .build());
        }
        return ResponseEntity.ok(anxietyColors);
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getAnxietyColorById(@PathVariable Integer id) {
        AnxietyColor anxietyColor = anxietyColorService.findById(id);
        if (anxietyColor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Anxiety color not found")
                    .build());
        }
        return ResponseEntity.ok(anxietyColor);
    }
}
