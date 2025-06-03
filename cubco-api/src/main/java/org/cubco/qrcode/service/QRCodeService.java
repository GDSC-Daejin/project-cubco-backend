package org.cubco.qrcode.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.jwt.JWTUtil;
import org.cubco.qrcode.QRCodeGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class QRCodeService {

    private final JWTUtil jwtUtil;
    private final QRCodeGenerator qrCodeGenerator;
    private final RedisTemplate<String, String> redisTemplate;

    // QR 코드 생성
    public String generateGuestQr(Long cafeId) {
        String token = jwtUtil.createQRToken(cafeId);
        String url = "https://cubco.com/stamp/guest?token=" + token;
        return qrCodeGenerator.generateQRCodeBase64(url, 300, 300);
    }

    // QR코드 유효성검사
    public Long validateAndUseQRToken(String token) {
        Claims claims = jwtUtil.getClaims(token);

        if (!"guest_qr".equals(claims.getSubject())) {
            throw new IllegalArgumentException("QR 전용 토큰이 아닙니다.");
        }

        String jti = claims.getId();
        String redisKey = "used_qr:" + jti;
        if (redisTemplate.hasKey(redisKey)) {
            throw new IllegalStateException("이미 사용된 QR입니다.");
        }

        // 사용 기록 저장
        redisTemplate.opsForValue().set(redisKey, "USED", 3, TimeUnit.MINUTES);

        return claims.get("cafeId", Long.class);
    }
}

