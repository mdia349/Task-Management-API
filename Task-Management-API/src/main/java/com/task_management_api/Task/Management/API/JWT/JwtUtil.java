package com.task_management_api.Task.Management.API.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final String SECRET = "88ff492cf632e66fde32d747e9c64fc7";
    private final long EXPIRATION = 1000 * 60 * 15; //15 minutes

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaim(token).getSubject();
    }

    public String extractRoles(String token) {
        return parseClaim(token).get("roles", String.class);
    }

    public boolean validateToken(String token) {
        try {
            parseClaim(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
