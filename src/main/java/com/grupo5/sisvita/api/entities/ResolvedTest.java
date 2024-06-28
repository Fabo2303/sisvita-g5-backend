package com.grupo5.sisvita.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grupo5.sisvita.config.CustomDateDeserializer;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ResolvedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_test_id")
    private TemplateTest templateTest;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classification_id")
    @JsonIgnoreProperties({"templateTest"})
    private Classification classification;

    @OneToMany(mappedBy = "resolvedTest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"resolvedTest"})
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPaciente")
    private Patient paciente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idConsignacion")
    private Consignacion consignacion = null;
}
