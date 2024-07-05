package com.example.sisvita.api.answer.dto.response;

import com.example.sisvita.api.answer.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private Integer id;
    private Integer idResolvedTest;
    private Integer idQuestion;
    private Integer idAlternative;

    public static AnswerResponse fromEntity(Answer answer) {
        return new AnswerResponse(
                answer.getId(),
                answer.getResolvedTest().getId(),
                answer.getQuestion().getId(),
                answer.getAlternative().getId()
        );
    }
}
