package com.grupo5.sisvita.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grupo5.sisvita.config.CustomDateDeserializer;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Persona {
    @Id
    private String document;

    private String documentType;
    private String name;
    private String lastName;
    private String secondLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date birthDate;

    private String sex;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubigeo")
    private Ubigeo ubigeo;
}
