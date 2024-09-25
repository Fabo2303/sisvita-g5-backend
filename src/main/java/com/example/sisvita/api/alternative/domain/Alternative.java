package com.example.sisvita.api.alternative.domain;
import lombok.Data;

@Data
public class Alternative {
    private Integer id;
    private String description;
    private int score;
    private int invertedScore;
    private int idTemplateTest;
}
