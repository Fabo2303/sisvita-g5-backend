package com.example.sisvita.api.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDataPatient {
    private String statement;
    private String alternative;
}
