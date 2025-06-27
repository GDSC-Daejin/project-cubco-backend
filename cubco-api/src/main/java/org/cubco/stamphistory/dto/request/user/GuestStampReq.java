package org.cubco.stamphistory.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "비회원 스탬프 적립 요청 데이터")
public class GuestStampReq {
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 숫자만 포함하며 10~11자리여야 합니다.")
    @Schema(description = "비회원 전화번호 (숫자만 입력, '-' 없이)", example = "01012345678")
    private String phone;

    @NotNull
    @Schema(description = "카페 ID (QR 인증에서 받은 cafeId)", example = "1")
    private Long cafeId;

    @NotNull
    @Schema(description = "QR Key (UUID 형식)", example = "9ad06551-a815-4731-97fe-4114ca9e658a")
    private String qrKey;
}