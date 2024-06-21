package com.grupo5.sisvita.api.dto;

import com.grupo5.sisvita.api.entities.Classification;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassificationDTO {
    private Long id;
    private int minimum;
    private int maximum;
    private String interpretation;
    private Long idTemplateTest;

    public static ClassificationDTO fromEntity(Classification classification) {
        return new ClassificationDTO(
                classification.getId(),
                classification.getMinimum(),
                classification.getMaximum(),
                classification.getInterpretation(),
                classification.getTemplateTest().getId()
        );
    }
}
