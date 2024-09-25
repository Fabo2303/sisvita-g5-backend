package com.example.sisvita.api.question.domain;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String statement;
    private String image;
    private boolean inverted;
    private int idTemplateTest;
}
