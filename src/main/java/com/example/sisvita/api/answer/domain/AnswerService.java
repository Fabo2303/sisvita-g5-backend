package com.example.sisvita.api.answer.domain;

import com.example.sisvita.api.answer.dto.response.AnswerResponse;
import com.example.sisvita.api.answer.infrastructure.JpaAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final JpaAnswerRepository jpaAnswerRepository;

    @Autowired
    public AnswerService(JpaAnswerRepository jpaAnswerRepository) {
        this.jpaAnswerRepository = jpaAnswerRepository;
    }

    public void insert(Integer idQuestion, Integer idAlternative, Integer idResolvedTest) {
        jpaAnswerRepository.insert(idQuestion, idAlternative, idResolvedTest);
    }

    public Answer save(Answer answer) {
        return jpaAnswerRepository.save(answer);
    }

    public Answer findById(Integer id) {
        return jpaAnswerRepository.findById(id).orElse(null);
    }

    public List<Answer> findAll() {
        return jpaAnswerRepository.findAll();
    }

    public List<AnswerResponse> findAllAnswerResponse() {
        return jpaAnswerRepository.findAllAnswerResponse();
    }
}
