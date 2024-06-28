package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.AlternativeDTO;
import com.grupo5.sisvita.api.entities.Alternative;
import com.grupo5.sisvita.api.repositories.AlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlternativesService {
    @Autowired
    private AlternativeRepository alternativeRepository;

    public Alternative saveAlternative(Alternative alternative) {
        return alternativeRepository.save(alternative);
    }

    public Alternative findById(Long id) {
        return alternativeRepository.findById(id).orElse(null);
    }

    public List<Alternative> findAll() {
        return alternativeRepository.findAll();
    }

    public List<AlternativeDTO> findAllAlternatives() {
        List<Alternative> alternatives = alternativeRepository.findAll();
        return alternatives.stream()
                .map(AlternativeDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
