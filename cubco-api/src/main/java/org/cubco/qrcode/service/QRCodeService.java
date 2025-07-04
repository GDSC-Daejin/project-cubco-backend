package org.cubco.qrcode.service;

import lombok.RequiredArgsConstructor;
import org.cubco.qrcode.QRCodeGenerator;
import org.cubco.qrcode.dto.request.CafeQrGenerateReq;
import org.cubco.qrcode.dto.response.GuestAccessRes;
import org.cubco.qrcode.dto.response.GuestQrRes;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QRCodeService {

    private final QRCodeGenerator qrCodeGenerator;
    private final RedisTemplate<String, String> redisTemplate;
    private final QrKeyService qrKeyService;

    // QR 코드 생성
    public GuestQrRes generateQr(CafeQrGenerateReq request) {
        // QR Key 발급
        String qrKey = qrKeyService.generateQrKey(request.getCafeId());

        String url = "https://cubco.kro.kr/api/v1/guest-access/guest/" + qrKey;
        String base64Image = qrCodeGenerator.generateQRCodeBase64(url, 300, 300);

        return GuestQrRes.of(base64Image, qrKey, url);
    }

    // QR코드 유효성검사
    public GuestAccessRes validateQR(String qrKey) {
        // QR Key가 유효한지 검사
        Long cafeId = qrKeyService.validateQrKey(qrKey);

        return GuestAccessRes.of(cafeId, qrKey);
    }
}

