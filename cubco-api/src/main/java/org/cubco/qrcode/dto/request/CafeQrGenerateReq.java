package org.cubco.qrcode.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "QR 생성 요청 데이터")
public class CafeQrGenerateReq {
    @NotNull(message = "카페 ID가 비어있습니다.")
    @Schema(description = "카페 ID (사장님이 관리하는 카페 ID)", example = "1")
    private Long cafeId;
}