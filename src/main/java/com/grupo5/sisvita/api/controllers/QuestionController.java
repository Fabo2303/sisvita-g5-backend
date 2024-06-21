package com.grupo5.sisvita.api.controllers;

import com.grupo5.sisvita.api.dto.QuestionDTO;
import com.grupo5.sisvita.api.entities.Question;
import com.grupo5.sisvita.api.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/dto")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping()
    public List<Question> getAllQuestions2() {
        return questionService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id) {
        Question question = questionService.findById(id).orElse(null);
        return ResponseEntity.ok().body(question);
    }
}
