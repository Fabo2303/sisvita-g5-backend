package com.example.sisvita.api.gender.web;

import com.example.sisvita.api.gender.domain.Gender;
import com.example.sisvita.api.gender.domain.GenderService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_GENDER)
public class GenderController {
    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping()
    public ResponseEntity<String> saveGender(@RequestBody Gender gender) {
        Boolean savedGender = genderService.saveGender(gender);
        if (!savedGender) {
            throw new CustomException("Gender not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Gender saved");
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Gender> genders = genderService.findAll();
        if (genders.isEmpty()) {
            throw new CustomException("No genders found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenderById(@PathVariable Integer id) {
        Gender gender = genderService.findById(id);
        if (gender == null) {
            throw new CustomException("Gender not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(gender);
    }

}
