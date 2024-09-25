package com.example.sisvita.api.consignment.domain;

import com.example.sisvita.api.consignment.dto.ConsignmentRequest;
import com.example.sisvita.api.consignment.infrastructure.ConsignmentRepository;
import com.example.sisvita.api.resolvedtest.infrastructure.ResolvedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ConsignmentService {
    private final ConsignmentRepository consignmentRepository;
    private final ResolvedTestRepository resolvedTestRepository;

    @Autowired
    public ConsignmentService(
            ConsignmentRepository consignmentRepository,
            ResolvedTestRepository resolvedTestRepository
    ) {
        this.consignmentRepository = consignmentRepository;
        this.resolvedTestRepository = resolvedTestRepository;
    }

    public Boolean save(Consignment consignment) {
        return consignmentRepository.save(consignment);
    }

    public Consignment findById(Integer id) {
        return consignmentRepository.findById(id);
    }

    public List<Consignment> findAll() {
        return consignmentRepository.findAll();
    }

    public Boolean insert(ConsignmentRequest consignmentRequest) {
        ZonedDateTime limaTime = ZonedDateTime.now(ZoneId.of("America/Lima"));
        Timestamp timestamp = Timestamp.valueOf(limaTime.toLocalDateTime());
        Boolean savedConsignment = consignmentRepository.save(
                new Consignment(
                        null,
                        timestamp,
                        consignmentRequest.getObservation(),
                        consignmentRequest.getFundament(),
                        consignmentRequest.getIdPatient(),
                        consignmentRequest.getIdSpecialist(),
                        consignmentRequest.getIdTestResolved(),
                        consignmentRequest.getIdDiagnosis(),
                        consignmentRequest.getIdTreatment()
                )
        );
        Consignment consignment = consignmentRepository.findLastConsignment(
                consignmentRequest.getIdPatient(),
                consignmentRequest.getIdTestResolved()
        );
        resolvedTestRepository.updateConsignationId(consignment.getId(), consignmentRequest.getIdTestResolved());
        return savedConsignment;
    }

    public Consignment findLastConsignation(Integer idPatient, Integer idResolvedTest) {
        return consignmentRepository.findLastConsignment(idPatient, idResolvedTest);
    }
}
