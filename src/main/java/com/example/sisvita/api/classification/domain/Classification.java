package com.example.sisvita.api.classification.domain;

import com.example.sisvita.api.anxietycolor.domain.AnxietyColor;
import com.example.sisvita.api.templatetest.domain.TemplateTest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int minimum;
    private int maximum;
    private String interpretation;

    private int intensity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_anxiety_color")
    @JsonIgnoreProperties("classifications")
    private AnxietyColor anxietyColor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_template_test")
    @JsonIgnoreProperties({"questions", "alternatives", "classifications"})
    private TemplateTest templateTest;

}