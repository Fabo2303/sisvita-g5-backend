package com.example.sisvita.api.alternative.domain;

import com.example.sisvita.api.alternative.infrastructure.JpaAlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativeService {
    private final JpaAlternativeRepository jpaAlternativeRepository;

    @Autowired
    public AlternativeService(JpaAlternativeRepository jpaAlternativeRepository) {
        this.jpaAlternativeRepository = jpaAlternativeRepository;
    }

    public List<Alternative> findAll() {
        return jpaAlternativeRepository.findAll();
    }

    public Alternative findById(Integer id) {
        return jpaAlternativeRepository.findById(id).orElse(null);
    }

    public Alternative save(Alternative alternative) {
        return jpaAlternativeRepository.save(alternative);
    }
}
