package com.example.sisvita.api.person.domain;

import com.example.sisvita.api.documenttype.domain.DocumentTypeService;
import com.example.sisvita.api.gender.domain.GenderService;
import com.example.sisvita.api.person.dto.PersonRequest;
import com.example.sisvita.api.person.infrastructure.JpaPersonRepository;
import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import com.example.sisvita.api.ubigeo.domain.UbigeoService;
import com.example.sisvita.api.ubigeo.dto.UbigeoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    public final JpaPersonRepository jpaPersonRepository;
    public final UbigeoService ubigeoService;
    public final DocumentTypeService documentTypeService;
    public final GenderService genderService;

    @Autowired
    public PersonService(
            JpaPersonRepository jpaPersonRepository,
            UbigeoService ubigeoService,
            DocumentTypeService documentTypeService,
            GenderService genderService
    ) {
        this.jpaPersonRepository = jpaPersonRepository;
        this.ubigeoService = ubigeoService;
        this.documentTypeService = documentTypeService;
        this.genderService = genderService;
    }

    public Person savePerson(PersonRequest personaRequest) {
        Person persona = PersonRequest.toEntity(personaRequest);
        UbigeoRequest ubigeoRequest = personaRequest.getUbigeoRequest();
        if (ubigeoRequest != null) {
            Ubigeo ubigeo = ubigeoService.findUbigeoByDepartmentAndProvinceAndDistrictObject(
                    ubigeoRequest.getDepartment(),
                    ubigeoRequest.getProvince(),
                    ubigeoRequest.getDistrict()
            );
            persona.setUbigeo(ubigeo);
        }
        Integer gender = personaRequest.getGender();
        Integer documentType = personaRequest.getDocumentType();
        persona.setGender(genderService.findById(gender));
        persona.setDocumentType(documentTypeService.findById(documentType));
        return jpaPersonRepository.save(persona);
    }

    public Person findByDocument(String document) {
        return jpaPersonRepository.findById(document).orElse(null);
    }

    public List<Person> findAll() {
        return jpaPersonRepository.findAll();
    }
}
