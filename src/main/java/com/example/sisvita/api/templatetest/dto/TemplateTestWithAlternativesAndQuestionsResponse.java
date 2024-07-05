package com.example.sisvita.api.templatetest.dto;

import com.example.sisvita.api.alternative.dto.AlternativeResponse;
import com.example.sisvita.api.question.dto.QuestionResponse;
import com.example.sisvita.api.templatetest.domain.TemplateTest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TemplateTestWithAlternativesAndQuestionsResponse {
    private Integer id;
    private String name;
    private List<AlternativeResponse> alternatives;
    private List<QuestionResponse> questions;

    public static TemplateTestWithAlternativesAndQuestionsResponse fromEntity(TemplateTest templateTest) {
        return TemplateTestWithAlternativesAndQuestionsResponse.builder()
                .id(templateTest.getId())
                .name(templateTest.getName())
                .alternatives(AlternativeResponse.fromEntities(templateTest.getAlternatives()))
                .questions(QuestionResponse.fromEntities(templateTest.getQuestions()))
                .build();
    }
}
