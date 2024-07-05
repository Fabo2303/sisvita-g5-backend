package com.example.sisvita.api.diagnosis.infrastructure;

import com.example.sisvita.api.diagnosis.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
}
