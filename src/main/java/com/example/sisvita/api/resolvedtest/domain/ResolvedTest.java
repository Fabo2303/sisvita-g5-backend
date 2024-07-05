package com.example.sisvita.api.resolvedtest.domain;

import com.example.sisvita.api.answer.domain.Answer;
import com.example.sisvita.api.classification.domain.Classification;
import com.example.sisvita.api.consignation.domain.Consignation;
import com.example.sisvita.api.patient.domain.Patient;
import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.example.sisvita.config.deserializers.TimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ResolvedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_template_test")
    private TemplateTest templateTest;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_classification")
    @JsonIgnoreProperties({"templateTest"})
    private Classification classification;

    @OneToMany(mappedBy = "resolvedTest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"resolvedTest"})
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consignation")
    private Consignation consignation = null;

    @PrePersist
    protected void onCreate() {
        date = new Timestamp(System.currentTimeMillis());
    }
}