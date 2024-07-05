package com.example.sisvita.api.alternative.domain;

import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Alternative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private int score;
    private int invertedScore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_template_test")
    @JsonIgnoreProperties({"questions", "alternatives", "classifications"})
    private TemplateTest templateTest;
}
