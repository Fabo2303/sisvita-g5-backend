package com.example.sisvita.api.anxietycolor.web;

import com.example.sisvita.api.anxietycolor.domain.AnxietyColor;
import com.example.sisvita.api.anxietycolor.domain.AnxietyColorService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.ErrorResponse;
import com.example.sisvita.utilz.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_ANXIETY_COLOR)
public class AnxietyColorController {
    private final AnxietyColorService anxietyColorService;

    @Autowired
    public AnxietyColorController(AnxietyColorService anxietyColorService) {
        this.anxietyColorService = anxietyColorService;
    }

    @GetMapping()
    public ResponseEntity<List<AnxietyColor>> findAll() {
        List<AnxietyColor> anxietyColors = anxietyColorService.findAll();
        if (anxietyColors.isEmpty()) {
            throw new CustomException("Anxiety colors not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(anxietyColors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnxietyColor> findById(@PathVariable Integer id) {
        AnxietyColor anxietyColor = anxietyColorService.findById(id);
        if (anxietyColor == null) {
            throw new CustomException("Anxiety color not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(anxietyColor);
    }

    @PostMapping()
    public ResponseEntity<String> saveAnxietyColor(@RequestBody AnxietyColor anxietyColor) {
        Boolean savedAnxietyColor = anxietyColorService.save(anxietyColor);
        if (!savedAnxietyColor) {
            throw new CustomException("Anxiety color not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Anxiety color saved");
    }
}
