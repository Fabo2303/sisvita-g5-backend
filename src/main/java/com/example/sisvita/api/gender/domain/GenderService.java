package com.example.sisvita.api.gender.domain;

import com.example.sisvita.api.gender.infrastructure.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public Boolean saveGender(Gender gender){
        return genderRepository.save(gender);
    }

    public Gender findById(Integer id){
        return genderRepository.findById(id);
    }

    public List<Gender> findAll(){
        return genderRepository.findAll();
    }
}
