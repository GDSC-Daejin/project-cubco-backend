package org.cubco.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final int expiredms;

    @Autowired
    public JWTUtil(SecretKey secretKey, @Value("${jwt.expiration}")int expiredms){
        this.expiredms = expiredms;
        this.secretKey = secretKey;
    }

    public String getUserId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub", String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("roles", String.class);
    }

    public Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public Date getExpirationDate() {
        return new Date(getCurrentDate().getTime() + expiredms);
    }

    public Date getQRExpirationDate() {
        return new Date(getCurrentDate().getTime() + 3 * 60 * 1000L); // 3분
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        } else {
            token = token.substring(7);
            return token;
        }
    }

    public String createToken(Long userId, String role){
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .issuedAt(getCurrentDate())
                .expiration(getExpirationDate())
                .claim("roles", "ROLE_"+role)
                .signWith(secretKey)
                .compact();
    }

    public String createQRToken(Long cafeId){
        return Jwts.builder()
                .setSubject("guest_qr")
                .claim("cafeId", cafeId)
                .issuedAt(getCurrentDate())
                .expiration(getQRExpirationDate())
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
