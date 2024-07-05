package com.example.sisvita.api.treatment.infrastructure;

import com.example.sisvita.api.treatment.domain.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTreatmentRepository extends JpaRepository<Treatment, Integer> {

}
