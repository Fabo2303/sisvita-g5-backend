package com.example.sisvita.auth.domain;

import com.example.sisvita.api.patient.infrastructure.PatientRepository;
import com.example.sisvita.api.specialist.infrastructure.SpecialistRepository;
import com.example.sisvita.api.user.domain.User;
import com.example.sisvita.api.user.domain.UserService;
import com.example.sisvita.api.user.infrastructure.UserRepository;
import com.example.sisvita.auth.dto.AuthenticationRequest;
import com.example.sisvita.auth.dto.AuthenticationResponse;
import com.example.sisvita.utilz.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    public AuthenticationService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername());
        if (user == null) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }

        if (!userService.checkPassword(authRequest.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        if (!user.isEnabled()) {
            throw new CustomException("User is disabled", HttpStatus.UNAUTHORIZED);
        }

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        String id = String.valueOf(user.getId());
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRoleAuthority().name());
        //extraClaims.put("permissions", user.getAuthorities());
        extraClaims.put("id", id);
        return extraClaims;
    }
}
