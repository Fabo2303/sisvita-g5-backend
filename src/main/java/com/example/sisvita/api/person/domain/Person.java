package com.example.sisvita.api.person.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Person {
    private int id;
    private String document;
    private int idDocumentType;
    private String name;
    private String lastName;
    private String secondLastName;
    private Date birthDate;
    private int idGender;
    private String phone;
    private String email;
    private String ubigeo;
}
