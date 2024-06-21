package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.AlternativeDTO;
import com.grupo5.sisvita.api.entities.Alternative;
import com.grupo5.sisvita.api.services.AlternativesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/alternative")
public class AlternativeController {
    @Autowired
    private AlternativesService alternativeService;

    @GetMapping()
    public ResponseEntity<Iterable<Alternative>> findAll() {
        Iterable<Alternative> alternatives = alternativeService.findAll();
        return ResponseEntity.ok(alternatives);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<AlternativeDTO>> getAllAlternatives() {
        Iterable<AlternativeDTO> alternatives = alternativeService.findAllAlternatives();
        return ResponseEntity.ok(alternatives);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Alternative> findById(@PathVariable Long id) {
        Alternative alternative = alternativeService.findById(id);
        return ResponseEntity.ok().body(alternative);
    }
}
