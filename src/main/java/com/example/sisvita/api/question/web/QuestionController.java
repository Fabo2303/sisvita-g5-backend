package com.example.sisvita.api.question.web;

import com.example.sisvita.api.question.domain.Question;
import com.example.sisvita.api.question.domain.QuestionService;
import com.example.sisvita.utilz.CustomException;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.API_QUESTION)
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
        Boolean savedQuestion = questionService.saveQuestion(question);
        if (!savedQuestion) {
            throw new CustomException("Question not saved", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Question saved");
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            throw new CustomException("No questions found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new CustomException("Question not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(question);
    }
}
