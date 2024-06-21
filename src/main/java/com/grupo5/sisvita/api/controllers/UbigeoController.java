package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.entities.Ubigeo;
import com.grupo5.sisvita.api.services.UbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/ubigeo")
public class UbigeoController {
    @Autowired
    private UbigeoService ubigeoService;

    @PostMapping()
    public ResponseEntity<Ubigeo> createUbigeo(@RequestBody Ubigeo ubigeo) {
        Ubigeo savedUbigeo = ubigeoService.saveUbigeo(ubigeo);
        return ResponseEntity.ok(savedUbigeo);
    }

    @GetMapping("/{ubigeo}")
    public ResponseEntity<Ubigeo> getUbigeoByUbigeo(@PathVariable String ubigeo) {
        Ubigeo ubigeoEntity = ubigeoService.findByUbigeo(ubigeo);
        return ResponseEntity.ok(ubigeoEntity);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Ubigeo>> getAllUbigeos() {
        Iterable<Ubigeo> ubigeos = ubigeoService.findAll();
        return ResponseEntity.ok(ubigeos);
    }

    @GetMapping("/provincias")
    public ResponseEntity<Iterable<String>> getProvinciasByDepartamento(@RequestParam String departamento) {
        Iterable<String> provincias = ubigeoService.findProvinciasByDepartamento(departamento);
        return ResponseEntity.ok(provincias);
    }

    @GetMapping("/distritos")
    public ResponseEntity<Iterable<String>> getDistritosByDepartamentoAndProvincia(@RequestParam String departamento, @RequestParam String provincia) {
        Iterable<String> distritos = ubigeoService.findDistritosByDepartamentoAndProvincia(departamento, provincia);
        return ResponseEntity.ok(distritos);
    }

    @GetMapping("/ubigeo")
    public ResponseEntity<String> getUbigeoByDepartamentoAndProvinciaAndDistrito(@RequestParam String departamento, @RequestParam String provincia, @RequestParam String distrito) {
        String ubigeo = ubigeoService.findUbigeoByDepartamentoAndProvinciaAndDistrito(departamento, provincia, distrito);
        return ResponseEntity.ok(ubigeo);
    }
}
