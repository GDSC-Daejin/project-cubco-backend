package org.cubco.qrcode.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.auth.resolver.UserId;
import org.cubco.qrcode.dto.CafeQrGenerateReq;
import org.cubco.qrcode.dto.GuestQrRes;
import org.cubco.qrcode.service.QRCodeService;
import org.cubco.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/qr")
public class QRController {

    private final QRCodeService qrCodeService;

    // QR 코드 생성
    @PostMapping("/generate")
    @PreAuthorize("hasRole('MANAGER')") // MANAGER 권한이 있는 사용자만 접근 가능
    public CommonResponse<?> generateGuestStampQR(@RequestBody CafeQrGenerateReq request) {
        GuestQrRes response = qrCodeService.generateQr(request);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }
}
