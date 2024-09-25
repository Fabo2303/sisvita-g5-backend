package com.example.sisvita.api.resolvedtest.dto.response;


import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolvedTestResponseTableFormat {
    private Integer id;
    private String namePatient;
    private String lastNamePatient;
    private Timestamp date;
    private String nameTemplateTest;
    private String colorClassification;
}
