package com.example.sisvita.api.resolvedtest.domain;

import com.example.sisvita.api.answer.infrastructure.AnswerRepository;
import com.example.sisvita.api.classification.domain.ClassificationService;
import com.example.sisvita.api.consignment.infrastructure.ConsignmentRepository;
import com.example.sisvita.api.resolvedtest.dto.response.*;
import com.example.sisvita.api.resolvedtest.infrastructure.ResolvedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ResolvedTestService {
    private final ResolvedTestRepository resolvedTestRepository;
    private final AnswerRepository answerRepository;
    private final ConsignmentRepository consignmentRepository;

    @Autowired
    public ResolvedTestService(
            ResolvedTestRepository resolvedTestRepository,
            AnswerRepository answerRepository,
            ConsignmentRepository consignmentRepository
    ) {
        this.resolvedTestRepository = resolvedTestRepository;
        this.answerRepository = answerRepository;
        this.consignmentRepository = consignmentRepository;
    }

    public Boolean save(ResolvedTest resolvedTest) {
        return resolvedTestRepository.save(resolvedTest);
    }

    public ResolvedTest findById(Integer id) {
        return resolvedTestRepository.findById(id);
    }

    public List<ResolvedTestResponseViewPatient> findByPatientId(Integer id) {
        List<ResolvedTestResponseViewPatient> resolvedTests = resolvedTestRepository.findByIdPatientView(id);
        resolvedTests.forEach(
                rt -> {
                    rt.setAnswers(answerRepository.findAllAnswerResponseDataPatient(rt.getId()));
                    rt.setConsignment(consignmentRepository.findConsignmentResponseByIdResolvedTest(rt.getId()));
                }
        );
        return resolvedTests;
    }

    public List<ResolvedTest> findAll() {
        return resolvedTestRepository.findAll();
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

    /*public ResolvedTestReponseDataPatient findByIdAndReturnDataPatient(Integer id) {
        ResolvedTest resolvedTest = resolvedTestRepository.findById(id);
        assert resolvedTest != null;
        return ResolvedTestReponseDataPatient.fromEntity(resolvedTest);
    }*/

    /*public List<ResolvedTestResponseViewPatient> findAllResolvedTestResponseViewPatient() {
        List<ResolvedTest> resolvedTests = resolvedTestRepository.findAll();
        return ResolvedTestResponseViewPatient.fromEntityList(resolvedTests);
    }*/

    public List<ResolvedTestResponseTableFormat> findAllResolvedTestResponse() {
        List<ResolvedTestResponseTableFormat> resolvedTestResponseTableFormats = resolvedTestRepository.findAllResolvedTestsResponse();
        resolvedTestResponseTableFormats.forEach(
                resolvedTestResponseTableFormat -> {
                    resolvedTestResponseTableFormat.setDate(new Timestamp(resolvedTestResponseTableFormat.getDate().getTime() - 18000000));
                }
        );
        return resolvedTestResponseTableFormats;
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestName(String name) {
        return resolvedTestRepository.findByTemplateTestName(name);
    }

    public List<ResolvedTestResponseTableFormat> findByClassificationInterpretation(String interpretation) {
        return resolvedTestRepository.findByClassificationInterpretation(interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByTemplateTestNameAndClassificationInterpretation(String name, String interpretation) {
        return resolvedTestRepository.findByTemplateTestNameAndClassificationInterpretation(name, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestName(Date fechaInicio, Date fechaFin, String name) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestName(fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndClassificationInterpretation(fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetween(Date fechaInicio, Date fechaFin) {
        return resolvedTestRepository.findByDateBetween(fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseTableFormat> findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestNameAndClassificationInterpretation(fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findAllHeatMap() {
        return resolvedTestRepository.findAllResolvedTestHeatMap();
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenHeatMap(Date fechaInicio, Date fechaFin) {
        return resolvedTestRepository.findByDateBetweenHeatMap(fechaInicio, fechaFin);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameHeatMap(Date fechaInicio, Date fechaFin, String name) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestNameHeatMap(fechaInicio, fechaFin, name);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(Date fechaInicio, Date fechaFin, String name, String interpretation) {
        return resolvedTestRepository.findByDateBetweenAndTemplateTestNameAndClassificationInterpretationHeatMap(fechaInicio, fechaFin, name, interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameHeatMap(String name) {
        return resolvedTestRepository.findByTemplateTestNameHeatMap(name);
    }

    public List<ResolvedTestResponseHeatMap> findByClassificationInterpretationHeatMap(String interpretation) {
        return resolvedTestRepository.findByClassificationInterpretationHeatMap(interpretation);
    }

    public List<ResolvedTestResponseHeatMap> findByTemplateTestNameAndClassificationInterpretationHeatMap(String name, String interpretation) {
        return resolvedTestRepository.findByTemplateTestNameAndClassificationInterpretationHeatMap(name, interpretation);
    }
}
