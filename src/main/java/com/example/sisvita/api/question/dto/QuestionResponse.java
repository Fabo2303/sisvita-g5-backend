package com.example.sisvita.api.question.dto;

import com.example.sisvita.api.question.domain.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class QuestionResponse {
    private Integer id;
    private String statement;
    private Boolean inverted;

    public static QuestionResponse fromEntity(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .statement(question.getStatement())
                .inverted(question.isInverted())
                .build();
    }

    public static List<QuestionResponse> fromEntities(List<Question> questions) {
        return questions.stream()
                .map(QuestionResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

