package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.QuestionDTO;
import com.grupo5.sisvita.api.entities.Question;
import com.grupo5.sisvita.api.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<QuestionDTO> findAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(QuestionDTO::fromEntity).collect(Collectors.toList());
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
