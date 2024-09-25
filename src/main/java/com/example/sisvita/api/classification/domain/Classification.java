package com.example.sisvita.api.classification.domain;

import lombok.Data;

@Data
public class Classification {
    private Integer id;
    private int minimum;
    private int maximum;
    private String interpretation;
    private int intensity;
    private int idAnxietyColor;
    private int idTemplateTest;
}