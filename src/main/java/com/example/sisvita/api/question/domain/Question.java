package com.example.sisvita.api.question.domain;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String statement;
    private String image;
    private boolean inverted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_template_test")
    @JsonIgnoreProperties({"questions", "alternatives", "classifications"})
    private TemplateTest templateTest;
}
