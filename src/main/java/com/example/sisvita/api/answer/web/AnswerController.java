package com.example.sisvita.api.answer.web;

import com.example.sisvita.api.answer.domain.Answer;
import com.example.sisvita.api.answer.domain.AnswerService;
import com.example.sisvita.api.answer.dto.response.AnswerResponse;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Answer> answers = answerService.findAll();
        if (answers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Answers not found").build());
        }
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/response")
    public ResponseEntity<?> findAllAnswerResponse() {
        List<AnswerResponse> answers = answerService.findAllAnswerResponse();
        if (answers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Answers not found").build());
        }
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Answer answer = answerService.findById(id);
        if (answer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Answer not found").build());
        }
        return ResponseEntity.ok().body(answer);
    }

    @PostMapping()
    public ResponseEntity<Answer> saveAnswer(@RequestBody Answer answer) {
        Answer savedAnswer = answerService.save(answer);
        return ResponseEntity.ok().body(savedAnswer);
    }
}
