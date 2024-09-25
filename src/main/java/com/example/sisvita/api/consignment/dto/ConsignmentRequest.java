package com.example.sisvita.api.consignment.dto;

import com.example.sisvita.config.deserializers.TimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsignmentRequest {

    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;

    private String observation;
    private String fundament;
    private Integer idPatient;
    private Integer idSpecialist;
    private Integer idTestResolved;
    private Integer idDiagnosis;
    private Integer idTreatment;
}
