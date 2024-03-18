package com.cursos.springsecurity.auth.auth.security.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${settings.auth.token-time}")
    private Integer EXPIRATION_MINUTES;

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String userName){
        Map<String, Object> extraClaims = new HashMap<>();

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userName)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(key)
                .compact();
    }


    public String extractUsername(String jwt) {
        return Jwts
                .parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody().getSubject();

    }
}
