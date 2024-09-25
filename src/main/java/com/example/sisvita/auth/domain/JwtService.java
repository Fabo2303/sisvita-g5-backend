package com.example.sisvita.auth.domain;

import com.example.sisvita.api.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(User user, Map<String, Object> claims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + jwtExpiration);
        return Jwts.builder()
                .claims().empty().add(claims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .and()
                .header().add("typ", "JWT").and()
                .signWith(generateKey())
                .compact();
    }

    public SecretKey generateKey() {
        byte[] secretAsBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
    }

    public String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public Boolean verifyIdInClaims(String authorizationHeader, Integer userId) {
        String jwt = extractTokenFromHeader(authorizationHeader);
        Claims claims = extractAllClaims(jwt);
        return userId.equals(claims.get("id", Integer.class));
    }

    public String extractRole(String jwt) {
        Claims claims = extractAllClaims(jwt);
        return (String) claims.get("role");
    }

    public boolean validateToken(String jwt, String username) {
        final String tokenUsername = extractUsername(jwt);
        return (tokenUsername.equals(username) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        final Date expiration = extractAllClaims(jwt).getExpiration();
        return expiration.before(new Date());
    }
}
