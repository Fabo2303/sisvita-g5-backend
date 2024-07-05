package com.example.sisvita.auth.web;

import com.example.sisvita.api.user.domain.User;
import com.example.sisvita.api.user.infrastructure.JpaUserRepository;
import com.example.sisvita.auth.domain.AuthenticationService;
import com.example.sisvita.auth.dto.AuthenticationRequest;
import com.example.sisvita.auth.dto.AuthenticationResponse;
import com.example.sisvita.utilz.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JpaUserRepository userRepository;

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AuthenticationRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername()).orElse(null);
        Map<String, String> responseBody= new HashMap<>();

        if (user == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("User not found").build());
        }

        AuthenticationResponse response = authenticationService.login(authRequest);

        if (response.getJwt() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Password incorrect").build());
        }

        if (!user.isEnabled()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.builder().message("User not enabled").build());
        }

        responseBody.put("jwt", response.getJwt());
        return ResponseEntity.ok(responseBody);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public ResponseEntity<?> publicAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "public access");
        return ResponseEntity.ok(response);
    }
}
