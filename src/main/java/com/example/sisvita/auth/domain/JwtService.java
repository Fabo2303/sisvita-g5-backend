package com.example.sisvita.auth.domain;

import com.example.sisvita.api.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    public String generateToken(User user, Map<String, Object> claims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8);
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
        byte[] secretAsBytes = Decoders.BASE64.decode("5Rq7K1SXQD2xx/jY0Chyf1LnzBmOqz/R3bENMlAMAws=");
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
    }
}
