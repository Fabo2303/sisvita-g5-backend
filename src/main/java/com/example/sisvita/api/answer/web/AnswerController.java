package com.example.sisvita.api.answer.web;

import com.example.sisvita.api.answer.domain.Answer;
import com.example.sisvita.api.answer.domain.AnswerService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_ANSWER)
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    public ResponseEntity<List<Answer>> findAll() {
        List<Answer> answers = answerService.findAll();
        if (answers.isEmpty()) {
            throw new CustomException("Answers not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findById(@PathVariable Integer id) {
        Answer answer = answerService.findById(id);
        if (answer == null) {
            throw new CustomException("Answer not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(answer);
    }

    @PostMapping()
    public ResponseEntity<String> saveAnswer(@RequestBody Answer answer) {
        Boolean savedAnswer = answerService.save(answer);
        if (!savedAnswer) {
            throw new CustomException("Answer not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Answer saved");
    }
}
