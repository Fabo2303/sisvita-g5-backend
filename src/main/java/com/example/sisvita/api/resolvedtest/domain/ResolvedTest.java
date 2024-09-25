package com.example.sisvita.api.resolvedtest.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResolvedTest {
    private Integer id;
    private int result;
    private Timestamp date;
    private int idTemplateTest;
    private int idClassification;
    private int idPatient;
    private int idConsignment;
}