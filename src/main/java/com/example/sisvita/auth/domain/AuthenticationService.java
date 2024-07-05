package com.example.sisvita.auth.domain;

import com.example.sisvita.api.patient.infrastructure.JpaPatientRepository;
import com.example.sisvita.api.specialist.infrastructure.JpaSpecialistRepository;
import com.example.sisvita.api.user.domain.User;
import com.example.sisvita.api.user.infrastructure.JpaUserRepository;
import com.example.sisvita.auth.dto.AuthenticationRequest;
import com.example.sisvita.auth.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private JpaSpecialistRepository specialistRepository;

    @Autowired
    private JpaPatientRepository patientRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());
        User user;
        try {
            authenticationManager.authenticate(authToken);
            user = userRepository.findByUsername(authRequest.getUsername()).orElse(null);
        } catch (Exception e) {
            return new AuthenticationResponse(null);
        }

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        String id = "";
        if (user.getRole().name().equals("SPECIALIST")){
            id = specialistRepository.findIdByIdUser(user.getId()).toString();
        } else if (user.getRole().name().equals("PATIENT")){
            id = patientRepository.findIdByIdUser(user.getId()).toString();
        }
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());
        extraClaims.put("id", id);
        return extraClaims;
    }
}
