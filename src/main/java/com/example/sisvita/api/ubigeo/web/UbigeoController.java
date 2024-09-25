package com.example.sisvita.api.ubigeo.web;

import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import com.example.sisvita.api.ubigeo.domain.UbigeoService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_UBIGEO)
public class UbigeoController {
    private final UbigeoService ubigeoService;

    public UbigeoController(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    @PostMapping()
    public ResponseEntity<String> saveUbigeo(@RequestBody Ubigeo ubigeo) {
        Boolean savedUbigeo = ubigeoService.saveUbigeo(ubigeo);
        if (!savedUbigeo) {
            throw new CustomException("Ubigeo not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Ubigeo saved");
    }

    @GetMapping()
    public ResponseEntity<List<Ubigeo>> getAllUbigeos() {
        List<Ubigeo> ubigeos = ubigeoService.findAll();
        if (ubigeos.isEmpty()) {
            throw new CustomException("No ubigeos found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ubigeos);
    }

    @GetMapping("/{ubigeo}")
    public ResponseEntity<Ubigeo> getUbigeoByUbigeo(@PathVariable String ubigeo) {
        Ubigeo ubigeoEntity = ubigeoService.findByUbigeo(ubigeo);
        if (ubigeoEntity == null) {
            throw new CustomException("Ubigeo not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(ubigeoEntity);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<String>> getDepartments() {
        List<String> departments = ubigeoService.findAllDepartments();
        if (departments.isEmpty()) {
            throw new CustomException("Departments not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<String>> getProvincesByDepartment(@RequestParam String department) {
        List<String> provinces = ubigeoService.findProvincesByDepartment(department);
        if (provinces.isEmpty()) {
            throw new CustomException("Provinces not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(provinces);
    }

    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistrictsByDepartmentAndProvince(@RequestParam String department, @RequestParam String province) {
        List<String> districts = ubigeoService.findDistrictsByDepartmentAndProvince(department, province);
        if (districts.isEmpty()) {
            throw new CustomException("Districts not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/ubigeo")
    public ResponseEntity<String> getUbigeoByDepartmentAndProvinceAndDistrict(@RequestParam String department, @RequestParam String province, @RequestParam String district) {
        String ubigeo = ubigeoService.findUbigeoByDepartmentAndProvinceAndDistrict(department, province, district);
        if (ubigeo == null) {
            throw new CustomException("Ubigeo not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ubigeo);
    }

    @GetMapping("/ubigeo-object")
    public ResponseEntity<Ubigeo> getUbigeoByDepartmentAndProvinceAndDistrictObject(@RequestParam String department, @RequestParam String province, @RequestParam String district) {
        Ubigeo ubigeo = ubigeoService.findUbigeoByDepartmentAndProvinceAndDistrictObject(department, province, district);
        if (ubigeo == null) {
            throw new CustomException("Ubigeo not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ubigeo);
    }
}
