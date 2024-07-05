package com.example.sisvita.api.resolvedtest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestResponseHeatMap {
    private Integer id;
    private String ubigeo;
    private String department;
    private double latitude;
    private double longitude;
    private int intensity;
    private long ubigeoIntensityCount;
}

