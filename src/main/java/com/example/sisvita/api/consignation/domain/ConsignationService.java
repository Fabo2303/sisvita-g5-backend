package com.example.sisvita.api.consignation.domain;

import com.example.sisvita.api.consignation.dto.ConsignationRequest;
import com.example.sisvita.api.consignation.dto.ConsignationResponse;
import com.example.sisvita.api.consignation.infrastructure.JpaConsignationRepository;
import com.example.sisvita.api.resolvedtest.infrastructure.JpaResolvedTestRepository;
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

    @Autowired
    public ConsignationService(
            JpaConsignationRepository jpaConsignationRepository,
            JpaResolvedTestRepository jpaResolvedTestRepository
    ) {
        this.jpaConsignationRepository = jpaConsignationRepository;
        this.jpaResolvedTestRepository = jpaResolvedTestRepository;
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
        Integer idConsignation = jpaConsignationRepository.findLastConsignation(
                consignationRequest.getIdPatient(),
                consignationRequest.getIdTestResolved()
        ).getId();
        jpaResolvedTestRepository.updateConsignationId(idConsignation, consignationRequest.getIdTestResolved());
    }

    public Consignation findLastConsignation(Integer idPatient, Integer idResolvedTest) {
        return jpaConsignationRepository.findLastConsignation(idPatient, idResolvedTest);
    }
}
