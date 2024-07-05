package com.example.sisvita.api.ubigeo.web;

import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import com.example.sisvita.api.ubigeo.domain.UbigeoService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ubigeo")
public class UbigeoController {
    private final UbigeoService ubigeoService;

    @Autowired
    public UbigeoController(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    @PostMapping()
    public ResponseEntity<?> saveUbigeo(@RequestBody Ubigeo ubigeo) {
        Ubigeo savedUbigeo = ubigeoService.saveUbigeo(ubigeo);
        return ResponseEntity.ok(savedUbigeo);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUbigeos() {
        List<Ubigeo> ubigeos = ubigeoService.findAll();
        if (ubigeos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ubigeos not found").build());
        }
        return ResponseEntity.ok(ubigeos);
    }

    @GetMapping("/{ubigeo}")
    public ResponseEntity<?> getUbigeoByUbigeo(@PathVariable String ubigeo) {
        Ubigeo ubigeoEntity = ubigeoService.findByUbigeo(ubigeo);
        if (ubigeoEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ubigeo not found").build());
        }
        return ResponseEntity.ok(ubigeoEntity);
    }

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments() {
        List<String> departments = ubigeoService.findAllDepartments();
        if (departments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Departments not found").build());
        }
        Map<String, List<String>> response = Map.of("departments", departments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/provinces")
    public ResponseEntity<?> getProvincesByDepartment(@RequestParam String department) {
        List<String> provinces = ubigeoService.findProvincesByDepartment(department);
        if (provinces.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Provinces not found").build());
        }
        Map<String, List<String>> response = Map.of("provinces", provinces);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/districts")
    public ResponseEntity<?> getDistrictsByDepartmentAndProvince(@RequestParam String department, @RequestParam String province) {
        List<String> districts = ubigeoService.findDistrictsByDepartmentAndProvince(department, province);
        if (districts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Districts not found").build());
        }
        Map<String, List<String>> response = Map.of("districts", districts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ubigeo")
    public ResponseEntity<?> getUbigeoByDepartmentAndProvinceAndDistrict(@RequestParam String department, @RequestParam String province, @RequestParam String district) {
        String ubigeo = ubigeoService.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district);
        if (ubigeo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ubigeo not found").build());
        }
        return ResponseEntity.ok(ubigeo);
    }

    @GetMapping("/ubigeo-object")
    public ResponseEntity<?> getUbigeoByDepartmentAndProvinceAndDistrictObject(@RequestParam String department, @RequestParam String province, @RequestParam String district) {
        Ubigeo ubigeo = ubigeoService.findUbigeoByDepartmentAndProvinceAndDistrictObject(department, province, district);
        if (ubigeo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Ubigeo not found").build());
        }
        return ResponseEntity.ok(ubigeo);
    }
}
