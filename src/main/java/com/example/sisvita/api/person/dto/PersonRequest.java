package com.example.sisvita.api.person.dto;

import com.example.sisvita.api.person.domain.Person;
import com.example.sisvita.api.ubigeo.dto.UbigeoRequest;
import com.example.sisvita.config.deserializers.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String document;
    private Integer documentType;
    private String name;
    private String lastName;
    private String secondLastName;

    @JsonDeserialize(using = DateDeserializer.class)
    private Date birthDate;

    private Integer gender;
    private String phone;
    private String email;
    private UbigeoRequest ubigeoRequest;

    public static Person toEntity(PersonRequest personRequest) {
        Person persona = new Person();
        persona.setDocument(personRequest.getDocument());
        persona.setName(personRequest.getName());
        persona.setLastName(personRequest.getLastName());
        persona.setSecondLastName(personRequest.getSecondLastName());
        persona.setBirthDate(personRequest.getBirthDate());
        persona.setPhone(personRequest.getPhone());
        persona.setEmail(personRequest.getEmail());
        return persona;
    }
}
