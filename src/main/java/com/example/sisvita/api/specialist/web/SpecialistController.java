package com.example.sisvita.api.specialist.web;

import com.example.sisvita.api.specialist.domain.Specialist;
import com.example.sisvita.api.specialist.domain.SpecialistService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialist")
public class SpecialistController {
    private final SpecialistService specialistService;

    @Autowired
    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @PostMapping()
    public ResponseEntity<?> createSpecialist(@RequestBody Specialist specialist) {
        Specialist savedSpecialist = specialistService.save(specialist);
        return ResponseEntity.ok(savedSpecialist);
    }

    @GetMapping()
    public ResponseEntity<?> getAllSpecialists() {
        List<Specialist> specialists = specialistService.findAll();
        if (specialists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Specialists not found")
                    .build());
        }
        return ResponseEntity.ok(specialists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecialistById(@PathVariable Integer id) {
        Specialist specialist = specialistService.findById(id);
        if (specialist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Specialist not found")
                    .build());
        }
        return ResponseEntity.ok(specialist);
    }
}
