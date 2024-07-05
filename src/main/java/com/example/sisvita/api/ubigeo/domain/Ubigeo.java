package com.example.sisvita.api.ubigeo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ubigeo {
    @Id
    private String ubigeo;

    private String department;
    private String province;
    private String district;
    private double latitude;
    private double longitude;
}
