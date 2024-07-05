package com.example.sisvita.api.templatetest.dto;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplateTestResponse {
    private Integer id;
    private String name;

    public static TemplateTestResponse fromEntity(TemplateTest templateTest) {
        return TemplateTestResponse.builder()
                .id(templateTest.getId())
                .name(templateTest.getName())
                .build();
    }
}