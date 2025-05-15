package org.cubco.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponImageUpdateRequest {
    private String imageUrl;

    public static CouponImageUpdateRequest of(Coupon coupon) {
        return new CouponImageUpdateRequest(
                coupon.getImageUrl()
        );
    }
}
