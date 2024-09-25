package com.example.sisvita.api.specialist.web;

import com.example.sisvita.api.specialist.domain.Specialist;
import com.example.sisvita.api.specialist.domain.SpecialistService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_SPECIALIST)
public class SpecialistController {
    private final SpecialistService specialistService;

    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @PostMapping()
    public ResponseEntity<String> createSpecialist(@RequestBody Specialist specialist) {
        Boolean savedSpecialist = specialistService.save(specialist);
        if (!savedSpecialist) {
            throw new CustomException("Specialist not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Specialist saved");
    }

    @GetMapping()
    public ResponseEntity<List<Specialist>> findAll() {
        List<Specialist> specialists = specialistService.findAll();
        if (specialists.isEmpty()) {
            throw new CustomException("No specialists found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(specialists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialist> getSpecialistById(@PathVariable Integer id) {
        Specialist specialist = specialistService.findById(id);
        if (specialist == null) {
            throw new CustomException("Specialist not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(specialist);
    }
}
