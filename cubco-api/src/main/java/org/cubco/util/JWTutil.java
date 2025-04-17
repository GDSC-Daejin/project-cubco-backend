package org.cubco.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTutil {

    private final SecretKey secretKey;

    @Autowired
    public JWTutil(SecretKey secretKey){
        this.secretKey = secretKey;
    }

    public String getUserId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub", String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("roles", String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createToken(Long userId, String role, Long expiredms){
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredms))
                .claim("roles", "ROLE_"+role)
                .signWith(secretKey)
                .compact();
    }

}
