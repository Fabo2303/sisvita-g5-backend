package com.example.sisvita.api.specialist.domain;

import com.example.sisvita.api.specialist.infrastructure.JpaSpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {
    private final JpaSpecialistRepository jpaSpecialistRepository;

    @Autowired
    public SpecialistService(JpaSpecialistRepository jpaSpecialistRepository) {
        this.jpaSpecialistRepository = jpaSpecialistRepository;
    }

    public Integer findIdByIdUser(Integer idUser) {
        return jpaSpecialistRepository.findIdByIdUser(idUser);
    }

    public Specialist save(Specialist specialist) {
        return jpaSpecialistRepository.save(specialist);
    }

    public Specialist findById(Integer id) {
        return jpaSpecialistRepository.findById(id).orElse(null);
    }

    public List<Specialist> findAll() {
        return jpaSpecialistRepository.findAll();
    }
}