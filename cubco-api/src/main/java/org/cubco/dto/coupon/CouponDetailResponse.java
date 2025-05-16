package org.cubco.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponDetailResponse {
    private Long couponId;
    private Long cafeId;
    private int stampCount;
    private String couponImageUrl;

    public static CouponDetailResponse of(Coupon coupon) {
        return new CouponDetailResponse(
                coupon.getId(),
                coupon.getCafe().getId(),
                coupon.getCount(),
                coupon.getImageUrl()
        );
    }
}
