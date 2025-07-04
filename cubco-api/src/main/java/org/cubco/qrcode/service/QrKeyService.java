package org.cubco.qrcode.service;

import lombok.RequiredArgsConstructor;
import org.cubco.exception.qrcode.QRAlreadyUsedException;
import org.cubco.exception.qrcode.QRTokenInvalidException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrKeyService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String PREFIX = "QR_KEY:";
    private static final Duration TTL = Duration.ofMinutes(3); // 유효시간 3분

    // QR Key 발급
    public String generateQrKey(Long cafeId) {
        String qrKey = UUID.randomUUID().toString();

        String redisKey = PREFIX + qrKey;
        redisTemplate.opsForValue().set(redisKey, cafeId.toString(), TTL);

        return qrKey;
    }

    // QR Key 유효성 검사
    public Long validateQrKey(String qrKey) {
        String redisKey = "QR_KEY:" + qrKey;
        String usedKey = "USED_QR_KEY:" + qrKey;

        // 사용 여부 먼저 확인
        if (Boolean.TRUE.equals(redisTemplate.hasKey(usedKey))) {
            throw new QRAlreadyUsedException();
        }

        // cafeId 추출
        String cafeIdStr = Optional.ofNullable(redisTemplate.opsForValue().get(redisKey))
                .orElseThrow(QRTokenInvalidException::new);

        return Long.parseLong(cafeIdStr);
    }
}
