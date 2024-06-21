package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.PersonaDTO;
import com.grupo5.sisvita.api.entities.Persona;
import com.grupo5.sisvita.api.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping()
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona savedPersona = personaService.savePersona(persona);
        return ResponseEntity.ok(savedPersona);
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<Persona> getPersonaByDocument(@PathVariable String document) {
        Persona persona = personaService.findByDocument(document);
        return ResponseEntity.ok(persona);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Persona>> getAllPersonas() {
        Iterable<Persona> personas = personaService.findAll();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/dto")
    public ResponseEntity<Iterable<PersonaDTO>> getAllPersonasDTO() {
        Iterable<PersonaDTO> personas = personaService.findAllPersonas();
        return ResponseEntity.ok(personas);
    }
}
