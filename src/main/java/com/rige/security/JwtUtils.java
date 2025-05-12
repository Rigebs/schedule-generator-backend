package com.rige.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    String jwtSecret;

    @Value("${jwt.expiration}")
    long jwtExpiration;

    public String createToken(Long id,
                              String email,
                              Long careerId,
                              Integer credits,
                              Collection<? extends GrantedAuthority> roles) {
        long expirationTime = jwtExpiration * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        List<String> roleNames = new ArrayList<>();
        for (GrantedAuthority role : roles) {
            roleNames.add(role.getAuthority());
        }

        return Jwts.builder()
                .claim("id", id)
                .claim("email", email)
                .claim("career", careerId)
                .claim("credits", credits)
                .claim("roles", roleNames)
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }


    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            @SuppressWarnings("unchecked")
            ArrayList<String> roles = (ArrayList<String>) claims.get("roles");

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            return new UsernamePasswordAuthenticationToken(email, null, authorities);
        } catch (JwtException e) {
            throw new JwtException("Invalid token: " + e.getMessage());
        }
    }
}