package com.example.sisvita.api.gender.web;

import com.example.sisvita.api.gender.domain.Gender;
import com.example.sisvita.api.gender.domain.GenderService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gender")
public class GenderController {
    private final GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping()
    public ResponseEntity<?> saveGender(@RequestBody Gender gender) {
        Gender savedGender = genderService.saveGender(gender);
        return ResponseEntity.ok(savedGender);
    }

    @GetMapping()
    public ResponseEntity<?> getAllGenders() {
        List<Gender> genders = genderService.findAll();
        if (genders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Genders not found").build());
        }
        return ResponseEntity.ok(genderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenderById(@PathVariable Integer id) {
        Gender gender = genderService.findById(id);
        if (gender == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Gender not found").build());
        }
        return ResponseEntity.ok(gender);
    }

}
