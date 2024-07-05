package com.example.sisvita.api.consignation.domain;

import com.example.sisvita.api.diagnosis.domain.Diagnosis;
import com.example.sisvita.api.patient.domain.Patient;
import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.example.sisvita.api.specialist.domain.Specialist;
import com.example.sisvita.api.treatment.domain.Treatment;
import com.example.sisvita.config.deserializers.TimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Consignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;

    private String observation;
    private String fundament;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_specialist")
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resolved_test")
    private ResolvedTest resolvedTest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diagnosis")
    private Diagnosis diagnosis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_treatment")
    private Treatment treatment;
}
