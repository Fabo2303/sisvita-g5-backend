package com.example.sisvita.api.resolvedtest.dto.response;

import com.example.sisvita.api.answer.dto.response.AnswerResponseDataPatient;
import com.example.sisvita.api.consignation.dto.ConsignationResponse;
import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestResponseViewPatient {
    private Integer id;
    private String nameTemplateTest;
    private String colorClassification;
    private String interpretation;
    private int result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp date;

    private List<AnswerResponseDataPatient> answers;
    private ConsignationResponse consignation;

    public static ResolvedTestResponseViewPatient fromEntity(ResolvedTest resolvedTest) {
        return ResolvedTestResponseViewPatient.builder()
                .id(resolvedTest.getId())
                .nameTemplateTest(resolvedTest.getTemplateTest().getName())
                .colorClassification(resolvedTest.getClassification().getAnxietyColor().getColor())
                .interpretation(resolvedTest.getClassification().getInterpretation())
                .result(resolvedTest.getResult())
                .date(
                        new Timestamp(resolvedTest.getDate().getTime() - 18000000)
                )
                .answers(AnswerResponseDataPatient.fromEntityList(resolvedTest.getAnswers()))
                .consignation(
                        resolvedTest.getConsignation() == null
                                ? null
                                : ConsignationResponse.fromEntity(resolvedTest.getConsignation())
                )
                .build();
    }

    public static List<ResolvedTestResponseViewPatient> fromEntityList(List<ResolvedTest> resolvedTests) {
        return resolvedTests.stream().map(ResolvedTestResponseViewPatient::fromEntity).toList();
    }

}
