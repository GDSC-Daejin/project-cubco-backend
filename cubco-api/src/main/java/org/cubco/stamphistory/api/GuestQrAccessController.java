package org.cubco.stamphistory.api;

import lombok.RequiredArgsConstructor;
import org.cubco.exception.CustomException;
import org.cubco.qrcode.service.QRCodeService;
import org.cubco.response.CommonResponse;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/guest/token")
public class GuestQrAccessController {

    private final QRCodeService qrCodeService;

    // 유저가 QR스캔 후 유효성검사,
    @GetMapping
    public CommonResponse<?> accessQR(@RequestParam String token) {
            Long cafeId = qrCodeService.validateAndUseQRToken(token);
            return CommonResponse.successWithData(HttpStatus.OK, cafeId);
    }
}