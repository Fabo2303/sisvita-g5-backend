package com.example.sisvita.api.consignation.domain;

import com.example.sisvita.api.consignation.dto.ConsignationRequest;
import com.example.sisvita.api.consignation.infrastructure.JpaConsignationRepository;
import com.example.sisvita.api.resolvedtest.infrastructure.JpaResolvedTestRepository;
import com.example.sisvita.utilz.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ConsignationService {
    private final JpaConsignationRepository jpaConsignationRepository;
    private final JpaResolvedTestRepository jpaResolvedTestRepository;
    private final EmailService emailService;

    @Autowired
    public ConsignationService(
            JpaConsignationRepository jpaConsignationRepository,
            JpaResolvedTestRepository jpaResolvedTestRepository,
            EmailService emailService
    ) {
        this.jpaConsignationRepository = jpaConsignationRepository;
        this.jpaResolvedTestRepository = jpaResolvedTestRepository;
        this.emailService = emailService;
    }

    public Consignation save(Consignation consignation) {
        return jpaConsignationRepository.save(consignation);
    }

    public Consignation findById(Integer id) {
        return jpaConsignationRepository.findById(id).orElse(null);
    }

    public List<Consignation> findAll() {
        return jpaConsignationRepository.findAll();
    }

    public void insert(ConsignationRequest consignationRequest) {
        ZonedDateTime limaTime = ZonedDateTime.now(ZoneId.of("America/Lima"));
        Timestamp timestamp = Timestamp.valueOf(limaTime.toLocalDateTime());
        jpaConsignationRepository.insertConsignation(
                timestamp,
                consignationRequest.getObservation(),
                consignationRequest.getFundament(),
                consignationRequest.getIdPatient(),
                consignationRequest.getIdSpecialist(),
                consignationRequest.getIdTestResolved(),
                consignationRequest.getIdDiagnosis(),
                consignationRequest.getIdTreatment()
        );
        Consignation consignation = jpaConsignationRepository.findLastConsignation(
                consignationRequest.getIdPatient(),
                consignationRequest.getIdTestResolved()
        );
        jpaResolvedTestRepository.updateConsignationId(consignation.getId(), consignationRequest.getIdTestResolved());
        emailService.sendSimpleEmail(
                consignation.getPatient().getUser().getPerson().getEmail(),
                "Consignación",
                buildEmailBody(consignation).toString()

        );
    }

    public StringBuilder buildEmailBody(Consignation consignation) {
        StringBuilder body = new StringBuilder();
        body.append("Fecha: ").append(consignation.getDate()).append("\n");
        body.append("Observación: ").append(consignation.getObservation()).append("\n");
        body.append("Fundamento: ").append(consignation.getFundament()).append("\n");
        body.append("Especialista: ").append(consignation.getSpecialist().getUser().getPerson().getName()).append(" ").append(consignation.getSpecialist().getUser().getPerson().getLastName()).append("\n");
        body.append("Diagnóstico: ").append(consignation.getDiagnosis().getName()).append("\n");
        body.append("Fundamento Diagnótico: ").append(consignation.getDiagnosis().getFundament()).append("\n");
        body.append("Tratamiento: ").append(consignation.getTreatment().getName()).append("\n");
        body.append("Fundamento Tratamiento: ").append(consignation.getTreatment().getFundament()).append("\n");
        body.append("Se le invita a realizar una cita para continuar con el tratamiento");
        return body;
    }

    public Consignation findLastConsignation(Integer idPatient, Integer idResolvedTest) {
        return jpaConsignationRepository.findLastConsignation(idPatient, idResolvedTest);
    }
}
