package com.example.sisvita.api.person.domain;

import com.example.sisvita.api.documenttype.domain.DocumentType;
import com.example.sisvita.api.gender.domain.Gender;
import com.example.sisvita.api.ubigeo.domain.Ubigeo;
import com.example.sisvita.config.deserializers.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Person {
    @Id
    private String document;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type")
    private DocumentType documentType;

    private String name;
    private String lastName;
    private String secondLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender")
    private Gender gender;

    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubigeo")
    private Ubigeo ubigeo;
}
