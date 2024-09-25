package com.example.sisvita.api.anxietycolor.domain;

import com.example.sisvita.api.anxietycolor.infrastructure.AnxietyColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnxietyColorService {
    private final AnxietyColorRepository anxietyColorRepository;

    public AnxietyColorService(AnxietyColorRepository anxietyColorRepository) {
        this.anxietyColorRepository = anxietyColorRepository;
    }

    public Boolean save(AnxietyColor anxietyColor) {
        return anxietyColorRepository.save(anxietyColor);
    }

    public AnxietyColor findById(Integer id) {
        return anxietyColorRepository.findById(id);
    }

    public List<AnxietyColor> findAll() {
        return anxietyColorRepository.findAll();
    }

}
