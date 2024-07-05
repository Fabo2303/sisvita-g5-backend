package com.example.sisvita.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/auth/public-access").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/alternative/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/alternative/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/classification/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/classification/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/patient/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/patient/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/person/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/person/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/specialist/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/specialist/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/question/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/question/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/resolved-test/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/resolved-test/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/ubigeo/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/ubigeo/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/user/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/user/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/template-test/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/template-test/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/consignation/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/consignation/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/document-type/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/gender/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/diagnosis/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/diagnosis/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/treatment/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/treatment/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/api/answer/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/answer/**").permitAll();
                });
        return http.build();
    }

}
