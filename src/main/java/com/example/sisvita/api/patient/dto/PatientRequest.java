package com.example.sisvita.api.patient.dto;

import com.example.sisvita.api.user.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private UserRequest userRequest;
}
