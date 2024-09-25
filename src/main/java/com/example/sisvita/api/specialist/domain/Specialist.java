package com.example.sisvita.api.specialist.domain;

import lombok.Data;

@Data
public class Specialist {
    private Integer id;
    private String license;
    private String specialty;
    private int idUser;
}
