package com.grupo5.sisvita.api.services;

import com.grupo5.sisvita.api.dto.response.RealizarVigilanciaDTO;
import com.grupo5.sisvita.api.dto.response.ResolvedTestDTO;
import com.grupo5.sisvita.api.entities.Alternative;
import com.grupo5.sisvita.api.entities.Answer;
import com.grupo5.sisvita.api.entities.Question;
import com.grupo5.sisvita.api.entities.ResolvedTest;
import com.grupo5.sisvita.api.repositories.ResolvedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResolvedTestService {
    @Autowired
    private ResolvedTestRepository resolvedTestRepository;

    public ResolvedTest saveResolvedTest(ResolvedTest resolvedTest) {
        return resolvedTestRepository.save(resolvedTest);
    }

    public ResolvedTest findById(Long id) {
        return resolvedTestRepository.findById(id).orElse(null);
    }

    public List<ResolvedTest> findAll() {
        return resolvedTestRepository.findAll();
    }

    public List<ResolvedTestDTO> findAllResolvedTests() {
        List<ResolvedTest> resolvedTests = resolvedTestRepository.findAll();
        return resolvedTests.stream()
                .map(ResolvedTestDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<RealizarVigilanciaDTO> realizarVigilancia(){
        List<ResolvedTest> resolvedTests = resolvedTestRepository.findAll();
        return resolvedTests.stream()
                .map(RealizarVigilanciaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ResolvedTest> findByTemplateTestName(String name) {
        return resolvedTestRepository.findByTemplateTestName(name);
    }

    public List<ResolvedTest> findByClassificationInterpretation(String interpretation) {
        return resolvedTestRepository.findByClassificationInterpretation(interpretation);
    }

    public List<ResolvedTest> findByTemplateTestNameAndClassificationInterpretation(String name, String interpretation) {
        return resolvedTestRepository.findByTemplateTestNameAndClassificationInterpretation(name, interpretation);
    }

    public List<ResolvedTest> findByDateBetweenAndTemplateTestName(Date fechaInicio, Date fechaFin, String name) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestName(fechaInicio, fechaFin, name);
    }

    public List<ResolvedTest> findByDateBetweenAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndClassificationInterpretation(fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTest> findByDateBetween(Date fechaInicio, Date fechaFin) {
        return resolvedTestRepository.findByDateBetween(fechaInicio, fechaFin);
    }

    public List<ResolvedTest> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicio, fechaFin, name, interpretation);
    }

    public int sumResultFromAlternatives(ResolvedTest resolvedTest) {
        List<Answer> answers = resolvedTest.getAnswers();

        List<Question> questions = answers.stream()
                .map(Answer::getQuestion)
                .toList();
        List<Alternative> alternatives = answers.stream()
                .map(Answer::getAlternative)
                .toList();
        int result = 0;
        for(int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            Alternative alternative = alternatives.get(i);
            if(question.isInverted()) {
                result += alternative.getInvertedScore();
            } else {
                result += alternative.getScore();
            }
        }
        return result;
    }
}
