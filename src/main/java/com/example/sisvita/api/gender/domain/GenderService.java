package com.example.sisvita.api.gender.domain;

import com.example.sisvita.api.gender.infrastructure.JpaGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {
    private final JpaGenderRepository genderRepository;

    @Autowired
    public GenderService(JpaGenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public Gender saveGender(Gender gender){
        return genderRepository.save(gender);
    }

    public Gender findById(Integer id){
        return genderRepository.findById(id).orElse(null);
    }

    public List<Gender> findAll(){
        return genderRepository.findAll();
    }
}
