package org.cubco.qrcode.api;

import lombok.RequiredArgsConstructor;
import org.cubco.qrcode.dto.GuestAccessRes;
import org.cubco.qrcode.service.QRCodeService;
import org.cubco.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guest-access")
public class GuestQrAccessController implements GuestQrAccessApiDocs{

    private final QRCodeService qrCodeService;

    // QR코드 유효성검사, cafeId 반환
    @GetMapping("/{qrKey}")
    public CommonResponse<?> accessQR(@PathVariable String qrKey) {
        GuestAccessRes guestAccessRes = qrCodeService.validateQR(qrKey);
            return CommonResponse.successWithData(HttpStatus.OK, guestAccessRes);
    }
}