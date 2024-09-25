package com.example.sisvita.api.question.domain;

import com.example.sisvita.api.question.infrastructure.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Boolean saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findById(Integer id) {
        return questionRepository.findById(id);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
