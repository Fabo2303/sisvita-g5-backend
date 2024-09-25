package com.example.sisvita.api.answer.domain;

import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private int idResolvedTest;
    private int idQuestion;
    private int idAlternative;
}