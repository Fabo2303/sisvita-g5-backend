package com.example.sisvita.api.alternative.web;

import com.example.sisvita.api.alternative.domain.Alternative;
import com.example.sisvita.api.alternative.domain.AlternativeService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_ALTERNATIVE)
public class AlternativeController {
    private final AlternativeService alternativeService;

    public AlternativeController(AlternativeService alternativeService) {
        this.alternativeService = alternativeService;
    }

    @GetMapping()
    public ResponseEntity<List<Alternative>> findAll() {
        List<Alternative> alternatives = alternativeService.findAll();
        if (alternatives.isEmpty()) {
            throw new CustomException("No alternatives found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alternatives);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alternative> findById(@PathVariable Integer id) {
        Alternative alternative = alternativeService.findById(id);
        if (alternative == null) {
            throw new CustomException("Alternative not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(alternative);
    }

    @PostMapping()
    public ResponseEntity<String> saveAlternative(@RequestBody Alternative alternative) {
        Boolean savedAlternative = alternativeService.save(alternative);
        if (!savedAlternative) {
            throw new CustomException("Alternative not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Alternative saved");
    }
}
