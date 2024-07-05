package com.example.sisvita.api.question.web;

import com.example.sisvita.api.question.domain.Question;
import com.example.sisvita.api.question.domain.QuestionService;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Questions not found")
                    .build());
        }
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Question question = questionService.findById(id);
        if (question == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                    .message("Question not found")
                    .build());
        }
        return ResponseEntity.ok().body(question);
    }
}
