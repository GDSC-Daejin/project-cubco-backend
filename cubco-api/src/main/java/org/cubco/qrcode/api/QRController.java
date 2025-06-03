package org.cubco.qrcode.api;

import lombok.RequiredArgsConstructor;
import org.cubco.qrcode.service.QRCodeService;
import org.cubco.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qr")
public class QRController {

    private final QRCodeService qrCodeService;

    // QR 코드 생성
    @GetMapping("/guest-stamp")
    public CommonResponse<?> generateGuestStampQR(@RequestParam Long cafeId) {
        String qrBase64 = qrCodeService.generateGuestQr(cafeId);
        return CommonResponse.successWithData(HttpStatus.OK, qrBase64);
    }
}
