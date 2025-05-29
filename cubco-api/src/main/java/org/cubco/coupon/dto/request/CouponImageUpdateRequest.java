package org.cubco.coupon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponImageUpdateRequest {

    @NotBlank(message = "변경할 이미지 파일이 없습니다.")
    @Schema(description = "변경할 이미지 URL", example = "https://img.com/sample.jpg")
    private String imageUrl;

}
