package com.example.sisvita.api.specialist.domain;

import com.example.sisvita.api.specialist.infrastructure.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {
    private final SpecialistRepository specialistRepository;

    @Autowired
    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    public Integer findIdByIdUser(Integer idUser) {
        return specialistRepository.findIdByIdUser(idUser);
    }

    public Boolean save(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    public Specialist findById(Integer id) {
        return specialistRepository.findById(id);
    }

    public List<Specialist> findAll() {
        return specialistRepository.findAll();
    }
}