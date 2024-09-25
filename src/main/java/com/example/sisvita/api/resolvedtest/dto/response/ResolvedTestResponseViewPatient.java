package com.example.sisvita.api.resolvedtest.dto.response;

import com.example.sisvita.api.answer.dto.response.AnswerResponseDataPatient;
import com.example.sisvita.api.consignment.dto.ConsignmentResponse;
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
    private Timestamp date;
    private List<AnswerResponseDataPatient> answers;
    private ConsignmentResponse consignment;

}
