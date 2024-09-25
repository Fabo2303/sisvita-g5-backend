package com.example.sisvita.api.consignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consignment {
    private Integer id;
    private Timestamp date;
    private String observation;
    private String fundament;
    private int idPatient;
    private int idSpecialist;
    private int idResolvedTest;
    private int idDiagnosis;
    private int idTreatment;
}