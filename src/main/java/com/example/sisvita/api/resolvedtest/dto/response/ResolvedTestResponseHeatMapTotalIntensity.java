package com.example.sisvita.api.resolvedtest.dto.response;

import lombok.Data;

@Data
public class ResolvedTestResponseHeatMapTotalIntensity extends ResolvedTestResponseHeatMap{
    private long totalIntensity;

    public ResolvedTestResponseHeatMapTotalIntensity(Integer id, String ubigeo, String department, double latitude, double longitude, int intensity, long ubigeoIntensityCount, long totalIntensity) {
        super(id, ubigeo, department, latitude, longitude, intensity, ubigeoIntensityCount);
        this.totalIntensity = totalIntensity;
    }
}
