package org.cubco.coupon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponDetailRes {
    private Long couponId;
    private Long cafeId;
    private int stampCount;
    private String couponImageUrl;

    public static CouponDetailRes of(Coupon coupon) {
        return new CouponDetailRes(
                coupon.getId(),
                coupon.getCafe().getId(),
                coupon.getCount(),
                coupon.getImageUrl()
        );
    }
}
