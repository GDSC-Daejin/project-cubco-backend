package org.cubco.qrcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;
import org.cubco.coupon.dto.response.CouponImageUpdateRes;

@Getter
@AllArgsConstructor
@Schema(description = "QR 코드 생성 후 응답 데이터")
public class GuestQrRes {
    @Schema(description = "QR 코드 이미지 (base64 인코딩된 PNG)", example = "data:image/png;base64,iVBORw0...")
    private String qrImage;
    @Schema(description = "QR Key (UUID 형식)", example = "9ad06551-a815-4731-97fe-4114ca9e658a")
    private String qrKey;
    @Schema(description = "QR 접근 URL (유저가 QR 찍으면 접근하게 되는 URL)", example = "https://cubco.kro.kr/api/v1/guest-access/9ad06551-a815-4731-97fe-4114ca9e658a")
    private String qrUrl;

    public static GuestQrRes of(String qrImage, String qrKey, String qrUrl) {
        return new GuestQrRes(
                qrImage,
                qrKey,
                qrUrl
        );
    }
}