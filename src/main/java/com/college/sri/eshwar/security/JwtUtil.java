package com.college.sri.eshwar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "custom_shree_ishwar12345678912345678"; // Change this to a secure key
    private static final long VALIDITY = 5 * 60 * 1000; // 5 minutes

    // Encode secret key in Base64
    private static final byte[] SECRET_KEY_BYTES = Base64.getEncoder().encode(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {



        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY))
                .signWith(new SecretKeySpec(SECRET_KEY_BYTES, "HmacSHA256")) // Proper signing key
                .compact();
    }

    public boolean validateToken(String token, String username) {
        if (token == null || token.isEmpty()) {
            return false; // Token is null or empty
        }
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY_BYTES) // Use the correct secret key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
