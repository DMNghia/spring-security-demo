package com.nghia.springsecuritydemo.security;

import com.nghia.springsecuritydemo.dto.UserLoginPrinciple;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;
    @Value("${application.security.jwt.issuer}")
    private String issuer;

    public String generateToken(UserLoginPrinciple principle) {
        return buildToken(principle, jwtExpiration);
    }

//    public String generateToken

    private String buildToken(UserLoginPrinciple principle, long expiredTime) {
        byte[] secretKeyBytes = secretKey.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(secretKeyBytes);
        Map<String, Object> claims = buildClaims(principle);
        return Jwts.builder()
                .setIssuedAt(new Date())
                .addClaims(claims)
                .setSubject(principle.getName())
                .setIssuer(issuer)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .compact();
    }

    public UserLoginPrinciple getPrinciple(String token) {
        if (!StringUtils.hasText(token))
            return null;
        Claims bodyClaims = extractClaims(token);
        if (ObjectUtils.isEmpty(bodyClaims))
            return null;
        return UserLoginPrinciple.builder()
                .name(bodyClaims.getSubject())
                .email((String) bodyClaims.get("email"))
                .gender((boolean) bodyClaims.get("gender"))
                .reference((String) bodyClaims.get("reference"))
                .roles((List<String>) bodyClaims.get("roles"))
                .build();
    }

    private Claims extractClaims(String token) {
        if (!StringUtils.hasText(token))
            return null;
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Map<String, Object> buildClaims(UserLoginPrinciple principle) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", principle.getName());
        claims.put("gender", principle.isGender());
        claims.put("reference", principle.getReference());
        claims.put("email", principle.getEmail());
        claims.put("roles", principle.getRoles());
        return claims;
    }
}
