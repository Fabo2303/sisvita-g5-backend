package com.example.sisvita.api.consignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsignmentResponse {
    private Timestamp date;
    private String observation;
    private String fundament;
    private String nameSpecialist;
    private String nameDiagnosis;
    private String fundamentDiagnosis;
    private String nameTreatment;
    private String fundamentTreatment;
}
