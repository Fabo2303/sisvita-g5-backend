package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.AnswerDTO;
import com.grupo5.sisvita.api.entities.Answer;
import com.grupo5.sisvita.api.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer findById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public Iterable<Answer> findAll() {
        return answerRepository.findAll();
    }

    public List<AnswerDTO> findAllAnswers() {
        List<Answer> answers = answerRepository.findAll();
        return answers.stream()
                .map(AnswerDTO::fromEntity)
                .collect(Collectors.toList());
    }


}
