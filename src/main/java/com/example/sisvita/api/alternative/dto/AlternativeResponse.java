package com.example.sisvita.api.alternative.dto;

import com.example.sisvita.api.alternative.domain.Alternative;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AlternativeResponse {
    private Integer id;
    private String description;
    private int score;
    private int invertedScore;

    public static AlternativeResponse fromEntity(Alternative alternative) {
        return AlternativeResponse.builder()
                .id(alternative.getId())
                .description(alternative.getDescription())
                .score(alternative.getScore())
                .invertedScore(alternative.getInvertedScore())
                .build();
    }

    public static List<AlternativeResponse> fromEntities(List<Alternative> alternatives) {
        return alternatives.stream()
                .map(AlternativeResponse::fromEntity)
                .collect(Collectors.toList());
    }
}