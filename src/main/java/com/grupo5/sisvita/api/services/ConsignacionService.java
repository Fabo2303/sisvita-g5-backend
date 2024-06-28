package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.entities.Consignacion;
import com.grupo5.sisvita.api.repositories.ConsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsignacionService {
    @Autowired
    private ConsignacionRepository consignacionRepository;

    public Consignacion save(Consignacion consignacion) {
        return consignacionRepository.save(consignacion);
    }

    public Consignacion findById(Long id) {
        return consignacionRepository.findById(id).orElse(null);
    }
}
