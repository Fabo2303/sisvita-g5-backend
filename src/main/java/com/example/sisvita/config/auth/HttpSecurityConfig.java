package com.example.sisvita.config.auth;

import com.example.sisvita.utilz.Routes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter authenticationFilter;

    public HttpSecurityConfig(
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter authenticationFilter
    ) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(new AntPathRequestMatcher(Routes.H2_CONSOLE)).permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, Routes.AUTH_LOGIN).permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, Routes.AUTH_PUBLIC_ACCESS).permitAll();
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
