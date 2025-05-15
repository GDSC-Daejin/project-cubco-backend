package org.cubco.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponDetailResponse {
    private Long couponId;
    private int count;
    private String imageUrl;
    private Long cafeId;

    public static CouponDetailResponse of(Coupon coupon) {
        return new CouponDetailResponse(
                coupon.getId(),
                coupon.getCount(),
                coupon.getImageUrl(),
                coupon.getCafe().getId()
        );
    }
}
