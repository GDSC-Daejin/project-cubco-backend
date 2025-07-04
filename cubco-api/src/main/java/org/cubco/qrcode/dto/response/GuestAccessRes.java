package org.cubco.qrcode.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "QR 접근 후 응답 데이터")
public class GuestAccessRes {
    @Schema(description = "QR 코드로 접근한 카페의 ID", example = "1")
    private Long cafeId;

    @Schema(description = "QR Key (UUID 형식)", example = "9ad06551-a815-4731-97fe-4114ca9e658a")
    private String qrKey;

    public static GuestAccessRes of(Long cafeId, String qrKey) {
        return new GuestAccessRes(
                cafeId,
                qrKey
        );
    }
}