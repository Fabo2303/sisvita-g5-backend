package com.grupo5.sisvita.api.dto;

import com.grupo5.sisvita.api.entities.Alternative;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlternativeDTO {
    private Long id;
    private String description;
    private int score;
    private int invertedScore;
    private Long idTemplateTest;

    public static AlternativeDTO fromEntity(Alternative alternative) {
        return AlternativeDTO.builder()
                .id(alternative.getId())
                .description(alternative.getDescription())
                .score(alternative.getScore())
                .invertedScore(alternative.getInvertedScore())
                .idTemplateTest(alternative.getTemplateTest().getId())
                .build();
    }
}
