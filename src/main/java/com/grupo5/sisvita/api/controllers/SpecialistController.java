package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.SpecialistDTO;
import com.grupo5.sisvita.api.entities.Specialist;
import com.grupo5.sisvita.api.services.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/specialist")
public class SpecialistController {
    @Autowired
    private SpecialistService specialistService;

    @PostMapping()
    public ResponseEntity<Specialist> createSpecialist(@RequestBody Specialist specialist) {
        Specialist savedSpecialist = specialistService.saveSpecialist(specialist);
        return ResponseEntity.ok(savedSpecialist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialist> getSpecialistById(@PathVariable Long id) {
        Specialist specialist = specialistService.findById(id);
        return ResponseEntity.ok(specialist);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Specialist>> getAllSpecialists() {
        Iterable<Specialist> specialists = specialistService.findAll();
        return ResponseEntity.ok(specialists);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<SpecialistDTO>> getAllSpecialistsDTO() {
        Iterable<SpecialistDTO> specialists = specialistService.findAllSpecialists();
        return ResponseEntity.ok(specialists);
    }
}
