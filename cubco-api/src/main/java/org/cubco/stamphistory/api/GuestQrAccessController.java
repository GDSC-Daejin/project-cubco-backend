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
@RequestMapping("/api/v1/guest-access")
public class GuestQrAccessController {

    private final QRCodeService qrCodeService;

    // QR코드 유효성검사, cafeId 반환
    @GetMapping("/{qrKey}")
    public CommonResponse<?> accessQR(@PathVariable String qrKey) {
            Long cafeId = qrCodeService.validateQR(qrKey);
            return CommonResponse.successWithData(HttpStatus.OK, cafeId);
    }
}