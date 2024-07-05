package com.example.sisvita.api.consignation.dto;

import com.example.sisvita.api.consignation.domain.Consignation;
import com.example.sisvita.config.deserializers.TimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ConsignationResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;

    private String observation;
    private String fundament;
    private String nameSpecialist;
    private String nameDiagnosis;
    private String fundamentDiagnosis;
    private String nameTreatment;
    private String fundamentTreatment;

    public static ConsignationResponse fromEntity(Consignation consignation) {
        return ConsignationResponse.builder()
                .date(new Timestamp(consignation.getDate().getTime() - 18000000))
                .observation(consignation.getObservation())
                .fundament(consignation.getFundament())
                .nameSpecialist(consignation.getSpecialist().getUser().getPerson().getName())
                .nameDiagnosis(consignation.getDiagnosis().getName())
                .fundamentDiagnosis(consignation.getDiagnosis().getFundament())
                .nameTreatment(consignation.getTreatment().getName())
                .fundamentTreatment(consignation.getTreatment().getFundament())
                .build();
    }
}
