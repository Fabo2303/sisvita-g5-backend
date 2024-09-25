package com.example.sisvita.api.answer.domain;

import com.example.sisvita.api.answer.infrastructure.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Boolean save(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer findById(Integer id) {
        return answerRepository.findById(id);
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }
}
