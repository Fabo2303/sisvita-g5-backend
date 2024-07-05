package com.example.sisvita.api.resolvedtest.dto.response;

import com.example.sisvita.api.answer.dto.response.AnswerResponseDataPatient;
import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestReponseDataPatient {
    private Integer id;
    private Integer idPatient;
    private String documentType;
    private String document;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nameTemplateTest;
    private String colorClassification;
    private String interpretation;
    private int result;
    private List<AnswerResponseDataPatient> answers;

    public static ResolvedTestReponseDataPatient fromEntity(ResolvedTest resolvedTest) {
        return ResolvedTestReponseDataPatient.builder()
                .id(resolvedTest.getId())
                .idPatient(resolvedTest.getPatient().getId())
                .documentType(resolvedTest.getPatient().getUser().getPerson().getDocumentType().getType())
                .document(resolvedTest.getPatient().getUser().getPerson().getDocument())
                .name(resolvedTest.getPatient().getUser().getPerson().getName())
                .lastName(resolvedTest.getPatient().getUser().getPerson().getLastName())
                .secondLastName(resolvedTest.getPatient().getUser().getPerson().getSecondLastName())
                .nameTemplateTest(resolvedTest.getTemplateTest().getName())
                .colorClassification(resolvedTest.getClassification().getAnxietyColor().getColor())
                .interpretation(resolvedTest.getClassification().getInterpretation())
                .result(resolvedTest.getResult())
                .answers(AnswerResponseDataPatient.fromEntityList(resolvedTest.getAnswers()))
                .build();
    }

}
