package com.example.sisvita.api.answer.domain;

import com.example.sisvita.api.alternative.domain.Alternative;
import com.example.sisvita.api.question.domain.Question;
import com.example.sisvita.api.resolvedtest.domain.ResolvedTest;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resolved_test")
    private ResolvedTest resolvedTest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alternative")
    private Alternative alternative;
}