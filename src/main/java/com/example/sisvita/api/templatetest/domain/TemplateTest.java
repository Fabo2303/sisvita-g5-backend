package com.example.sisvita.api.templatetest.domain;

import com.example.sisvita.api.alternative.domain.Alternative;
import com.example.sisvita.api.classification.domain.Classification;
import com.example.sisvita.api.question.domain.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TemplateTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String author;

    @OneToMany(mappedBy = "templateTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alternative> alternatives = new ArrayList<>();

    @OneToMany(mappedBy = "templateTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "templateTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classification> classifications = new ArrayList<>();
}