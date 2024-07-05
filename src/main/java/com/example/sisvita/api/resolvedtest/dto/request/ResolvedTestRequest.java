package com.example.sisvita.api.resolvedtest.dto.request;

import com.example.sisvita.api.answer.dto.request.AnswerRequest;
import com.example.sisvita.config.deserializers.TimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ResolvedTestRequest {
    private Integer id;
    private Integer idTemplateTest;
    private Integer idPatient;

    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;

    private List<AnswerRequest> answers;
}
