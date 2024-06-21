package com.grupo5.sisvita.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grupo5.sisvita.api.entities.Persona;
import com.grupo5.sisvita.config.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PersonaDTO {
    private String document;
    private String documentType;
    private String name;
    private String lastName;
    private String secondLastName;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date birthDate;

    private String sex;
    private String phone;
    private String email;
    private String ubigeo;

    public static PersonaDTO fromEntity(Persona persona) {
        return new PersonaDTO(
            persona.getDocument(),
            persona.getDocumentType(),
            persona.getName(),
            persona.getLastName(),
            persona.getSecondLastName(),
            persona.getBirthDate(),
            persona.getSex(),
            persona.getPhone(),
            persona.getEmail(),
            persona.getUbigeo().getUbigeo()
        );
    }
}
