package com.example.sisvita.api.resolvedtest.dto.response;


import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestResponseTableFormat {
    private Integer id;
    private String namePatient;
    private String lastNamePatient;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp date;

    private String nameTemplateTest;
    private String colorClassification;

    public static ResolvedTestResponseTableFormat fromEntity(ResolvedTest resolvedTest) {
        return ResolvedTestResponseTableFormat.builder()
                .id(resolvedTest.getId())
                .namePatient(resolvedTest.getPatient().getUser().getPerson().getName())
                .lastNamePatient(resolvedTest.getPatient().getUser().getPerson().getName())
                .date(resolvedTest.getDate())
                .nameTemplateTest(resolvedTest.getTemplateTest().getName())
                .colorClassification(resolvedTest.getClassification().getAnxietyColor().getColor())
                .build();
    }
}
