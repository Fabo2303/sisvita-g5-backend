package com.example.sisvita.api.resolvedtest.domain;

import com.example.sisvita.api.answer.dto.request.AnswerRequest;
import com.example.sisvita.api.answer.infrastructure.JpaAnswerRepository;
import com.example.sisvita.api.classification.domain.ClassificationService;
import com.example.sisvita.api.resolvedtest.dto.request.ResolvedTestRequest;
import com.example.sisvita.api.resolvedtest.dto.response.*;
import com.example.sisvita.api.resolvedtest.infrastructure.JpaResolvedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ResolvedTestService {
    private final JpaResolvedTestRepository jpaResolvedTestRepository;
    private final ClassificationService classificationService;
    private final JpaAnswerRepository answerRepository;

    @Autowired
    public ResolvedTestService(JpaResolvedTestRepository jpaResolvedTestRepository, ClassificationService classificationService, JpaAnswerRepository answerRepository) {
        this.jpaResolvedTestRepository = jpaResolvedTestRepository;
        this.classificationService = classificationService;
        this.answerRepository = answerRepository;
    }

    public Map<String, ?> saveResolvedTest(ResolvedTestRequest resolvedTestRequest) {
        int result = sumResultFromAlternatives(resolvedTestRequest.getAnswers());

        Integer classificationId = classificationService.findByTemplateTestIdAndResult(resolvedTestRequest.getIdTemplateTest(), result).getId();

        ZonedDateTime limaTime = ZonedDateTime.now(ZoneId.of("America/Lima"));

        Timestamp timestamp = Timestamp.valueOf(limaTime.toLocalDateTime());

        jpaResolvedTestRepository.insert(timestamp, result, resolvedTestRequest.getIdTemplateTest(), classificationId, resolvedTestRequest.getIdPatient());

        ResolvedTest resolvedTest = jpaResolvedTestRepository.findTopByPacienteIdOrderByDateDesc(resolvedTestRequest.getIdPatient());

        for (AnswerRequest answerRequest : resolvedTestRequest.getAnswers()) {
            answerRepository.insert(answerRequest.getIdQuestion(), answerRequest.getIdAlternative(), resolvedTest.getId());
        }

        return Map.of("result", resolvedTest.getResult(), "interpretation", resolvedTest.getClassification().getInterpretation(), "color", resolvedTest.getClassification().getAnxietyColor().getColor());
    }

    public int sumResultFromAlternatives(List<AnswerRequest> answerRequests) {
        int result = 0;
        for (AnswerRequest answerRequest : answerRequests) {
            if (answerRequest.isInverted()) {
                result += answerRequest.getInvertedScore();
            } else {
                result += answerRequest.getScore();
            }
        }
        return result;
    }

    public ResolvedTest findById(Integer id) {
        return jpaResolvedTestRepository.findById(id).orElse(null);
    }

    public List<ResolvedTestResponseViewPatient> findByPatientId(Integer id) {
        List<ResolvedTest> resolvedTests = jpaResolvedTestRepository.findByIdPatient(id);
        return ResolvedTestResponseViewPatient.fromEntityList(resolvedTests);
    }

    public List<ResolvedTest> findAll() {
        return jpaResolvedTestRepository.findAll();
    }

    public ResolvedTestResponseHeatMapTotalIntensity fromResolvedTestResponseHeatMap(ResolvedTestResponseHeatMap resolvedTestResponseHeatMap) {
        return new ResolvedTestResponseHeatMapTotalIntensity(
                resolvedTestResponseHeatMap.getId(),
                resolvedTestResponseHeatMap.getUbigeo(),
                resolvedTestResponseHeatMap.getDepartment(),
                resolvedTestResponseHeatMap.getLatitude(),
                resolvedTestResponseHeatMap.getLongitude(),
                resolvedTestResponseHeatMap.getIntensity(),
                resolvedTestResponseHeatMap.getUbigeoIntensityCount(),
                resolvedTestResponseHeatMap.getIntensity() * resolvedTestResponseHeatMap.getUbigeoIntensityCount()
        );
    }

    public ResolvedTestReponseDataPatient findByIdAndReturnDataPatient(Integer id) {
        ResolvedTest resolvedTest = jpaResolvedTestRepository.findById(id).orElse(null);
        assert resolvedTest != null;
        return ResolvedTestReponseDataPatient.fromEntity(resolvedTest);
    }

    public List<ResolvedTestResponseViewPatient> findAllResolvedTestResponseViewPatient() {
        List<ResolvedTest> resolvedTests = jpaResolvedTestRepository.findAll();
        return ResolvedTestResponseViewPatient.fromEntityList(resolvedTests);
    }

    public List<ResolvedTestResponseTableFormat> findAllResolvedTestResponse() {
        List<ResolvedTestResponseTableFormat> resolvedTestResponseTableFormats = jpaResolvedTestRepository.findAllResolvedTestsResponse();
        resolvedTestResponseTableFormats.forEach(
                resolvedTestResponseTableFormat -> {
                    resolvedTestResponseTableFormat.setDate(new Timestamp(resolvedTestResponseTableFormat.getDate().getTime() - 18000000));
                }
        );
        return resolvedTestResponseTableFormats;
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestName(String name) {
        return jpaResolvedTestRepository.findByTemplateTestName(name);
    }

    public List<ResolvedTestResponseTableFormat> findByClassificationInterpretation(String interpretation) {
        return jpaResolvedTestRepository.findByClassificationInterpretation(interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestNameAndClassificationInterpretation(String name, String interpretation) {
        return jpaResolvedTestRepository.findByTemplateTestNameAndClassificationInterpretation(name, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestName(Date fechaInicio, Date fechaFin, String name) {
        return jpaResolvedTestRepository.findByDateBetweenAndTemplateTestName(fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String interpretation) {
        return jpaResolvedTestRepository.findByDateBetweenAndClassificationInterpretation(fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetween(Date fechaInicio, Date fechaFin) {
        return jpaResolvedTestRepository.findByDateBetween(fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        return jpaResolvedTestRepository.findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findAllHeatMap() {
        return jpaResolvedTestRepository.findAllResolvedTestHeatMap();
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenHeatMap(Date fechaInicio, Date fechaFin) {
        return jpaResolvedTestRepository.findByDateBetweenHeatMap(fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameHeatMap(Date fechaInicio, Date fechaFin, String name) {
        return jpaResolvedTestRepository.findByDateBetweenAndTemplateTestNameHeatMap(fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String interpretation) {
        return jpaResolvedTestRepository.findByDateBetweenAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        return jpaResolvedTestRepository.findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameHeatMap(String name) {
        return jpaResolvedTestRepository.findByTemplateTestNameHeatMap(name);
    }

    public List<ResolvedTestResponseHeatMap> findByClassificationInterpretationHeatMap(String interpretation) {
        return jpaResolvedTestRepository.findByClassificationInterpretationHeatMap(interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameAndClassificationInterpretationHeatMap(String name, String interpretation) {
        return jpaResolvedTestRepository.findByTemplateTestNameAndClassificationInterpretationHeatMap(name, interpretation);
    }
}
