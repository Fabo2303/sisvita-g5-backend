package com.example.sisvita.api.ubigeo.domain;

import lombok.Data;

@Data
public class Ubigeo {
    private String ubigeo;
    private String department;
    private String province;
    private String district;
    private double latitude;
    private double longitude;
}
