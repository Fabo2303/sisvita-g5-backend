package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.PersonaDTO;
import com.grupo5.sisvita.api.dto.requests.PersonaRequest;
import com.grupo5.sisvita.api.dto.requests.UbigeoRequest;
import com.grupo5.sisvita.api.entities.Persona;
import com.grupo5.sisvita.api.entities.Ubigeo;
import com.grupo5.sisvita.api.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UbigeoService ubigeoService;

    public Persona savePersona(PersonaRequest personaRequest) {
        Persona persona = PersonaRequest.toEntity(personaRequest);
        UbigeoRequest ubigeoRequest = personaRequest.getUbigeoRequest();
        if (ubigeoRequest != null) {
            Ubigeo ubigeo = ubigeoService.findUbigeoByDepartamentoAndProvinciaAndDistritoObject(
                    ubigeoRequest.getDepartamento(),
                    ubigeoRequest.getProvincia(),
                    ubigeoRequest.getDistrito()
            );
            persona.setUbigeo(ubigeo);
        }
        return personaRepository.save(persona);
    }

    public Persona findByDocument(String document) {
        return personaRepository.findByDocument(document).orElse(null);
    }

    public Iterable<Persona> findAll() {
        return personaRepository.findAll();
    }

    public List<PersonaDTO> findAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream()
                .map(PersonaDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
