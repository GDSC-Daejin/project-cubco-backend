package org.cubco.coupon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponImageUpdateResponse {
    private Long couponId;
    private Long cafeId;
    private int stampCount;
    private String couponImageUrl;

    public static CouponImageUpdateResponse of(Coupon coupon) {
        return new CouponImageUpdateResponse(
                coupon.getId(),
                coupon.getCafe().getId(),
                coupon.getCount(),
                coupon.getImageUrl()
        );
    }
}