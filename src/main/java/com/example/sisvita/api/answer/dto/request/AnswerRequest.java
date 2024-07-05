package com.example.sisvita.api.answer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {
    private Integer idQuestion;
    private Integer idAlternative;
    private boolean inverted;
    private int score;
    private int invertedScore;
}
