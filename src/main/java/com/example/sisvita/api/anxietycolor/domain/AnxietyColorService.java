package com.example.sisvita.api.anxietycolor.domain;

import com.example.sisvita.api.anxietycolor.infrastructure.JpaAnxietyColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnxietyColorService {
    private final JpaAnxietyColorRepository jpaAnxietyColorRepository;

    @Autowired
    public AnxietyColorService(JpaAnxietyColorRepository jpaAnxietyColorRepository) {
        this.jpaAnxietyColorRepository = jpaAnxietyColorRepository;
    }

    public AnxietyColor save(AnxietyColor anxietyColor) {
        return jpaAnxietyColorRepository.save(anxietyColor);
    }

    public AnxietyColor findById(Integer id) {
        return jpaAnxietyColorRepository.findById(id).orElse(null);
    }

    public List<AnxietyColor> findAll() {
        return jpaAnxietyColorRepository.findAll();
    }

}
