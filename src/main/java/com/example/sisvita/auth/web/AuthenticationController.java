package com.example.sisvita.auth.web;

import com.example.sisvita.auth.domain.AuthenticationService;
import com.example.sisvita.auth.dto.AuthenticationRequest;
import com.example.sisvita.auth.dto.AuthenticationResponse;
import com.example.sisvita.utilz.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(Routes.AUTH)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PreAuthorize("permitAll")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated AuthenticationRequest authRequest) {
        AuthenticationResponse response = authenticationService.login(authRequest);
        return ResponseEntity.ok(new AuthenticationResponse(response.getJwt()));
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public ResponseEntity<Map<String, String>> publicAccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "public access");
        return ResponseEntity.ok(response);
    }
}
