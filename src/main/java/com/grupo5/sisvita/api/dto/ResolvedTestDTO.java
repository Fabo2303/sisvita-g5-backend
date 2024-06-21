package com.grupo5.sisvita.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grupo5.sisvita.api.entities.ResolvedTest;
import com.grupo5.sisvita.config.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResolvedTestDTO {
    private Long id;
    private int result;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    private Long templateTestId;
    private Long classificationId;

    public static ResolvedTestDTO fromEntity(ResolvedTest resolvedTest) {
        return new ResolvedTestDTO(
            resolvedTest.getId(),
            resolvedTest.getResult(),
            resolvedTest.getDate(),
            resolvedTest.getTemplateTest().getId(),
            resolvedTest.getClassification().getId()
        );
    }

    public ResolvedTest toEntity() {
        ResolvedTest resolvedTest = new ResolvedTest();
        resolvedTest.setId(this.id);
        resolvedTest.setDate(this.date);
        return resolvedTest;
    }
}
