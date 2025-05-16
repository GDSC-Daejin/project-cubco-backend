package org.cubco.dto.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponImageUpdateRequest {

    @Schema(description = "변경할 이미지 URL", example = "https://img.com/sample.jpg")
    private String imageUrl;

    public static CouponImageUpdateRequest of(Coupon coupon) {
        return new CouponImageUpdateRequest(
                coupon.getImageUrl()
        );
    }
}
