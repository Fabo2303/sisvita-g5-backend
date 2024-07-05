package com.example.sisvita.api.ubigeo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbigeoRequest {
    private String department;
    private String province;
    private String district;
}
