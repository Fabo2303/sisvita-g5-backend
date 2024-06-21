package com.grupo5.sisvita.api.dto;

import com.grupo5.sisvita.api.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private Long idUser;

    public static PatientDTO fromEntity(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getUser().getId());
    }
}
