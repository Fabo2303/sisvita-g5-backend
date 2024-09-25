package com.example.sisvita.api.alternative.domain;

import com.example.sisvita.api.alternative.infrastructure.AlternativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativeService {
    private final AlternativeRepository alternativeRepository;

    public AlternativeService(AlternativeRepository alternativeRepository) {
        this.alternativeRepository = alternativeRepository;
    }

    public List<Alternative> findAll() {
        return alternativeRepository.findAll();
    }

    public Alternative findById(Integer id) {
        return alternativeRepository.findById(id);
    }

    public Boolean save(Alternative alternative) {
        return alternativeRepository.save(alternative);
    }
}
