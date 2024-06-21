package com.grupo5.sisvita.api.dto;

import com.grupo5.sisvita.api.entities.TemplateTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TemplateTestDTO {
    private Long id;
    private String name;
    private String description;
    private String author;
    private List<AlternativeDTO> alternatives;
    private List<QuestionDTO> questions;
    private List<ClassificationDTO> classifications;

    public static TemplateTestDTO fromEntity(TemplateTest templateTest) {
        return new TemplateTestDTO(
                templateTest.getId(),
                templateTest.getName(),
                templateTest.getDescription(),
                templateTest.getAuthor(),
                templateTest.getAlternatives().stream().map(AlternativeDTO::fromEntity).toList(),
                templateTest.getQuestions().stream().map(QuestionDTO::fromEntity).toList(),
                templateTest.getClassifications().stream().map(ClassificationDTO::fromEntity).toList()
        );
    }
}
