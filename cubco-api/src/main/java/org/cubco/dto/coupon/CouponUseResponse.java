package org.cubco.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponUseResponse {
    private Long couponId;
    private Long cafeId;
    private int stampCount;
    private String couponImageUrl;

    public static CouponUseResponse of(Coupon coupon) {
        return new CouponUseResponse(
                coupon.getId(),
                coupon.getCafe().getId(),
                coupon.getCount(),
                coupon.getImageUrl()
        );
    }
}
