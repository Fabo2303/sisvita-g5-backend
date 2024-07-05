package com.example.sisvita.api.question.domain;

import com.example.sisvita.api.question.infrastructure.JpaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final JpaQuestionRepository jpaQuestionRepository;

    @Autowired
    public QuestionService(JpaQuestionRepository jpaQuestionRepository) {
        this.jpaQuestionRepository = jpaQuestionRepository;
    }

    public Question saveQuestion(Question question) {
        return jpaQuestionRepository.save(question);
    }

    public Question findById(Integer id) {
        return jpaQuestionRepository.findById(id).orElse(null);
    }

    public List<Question> findAll() {
        return jpaQuestionRepository.findAll();
    }
}
