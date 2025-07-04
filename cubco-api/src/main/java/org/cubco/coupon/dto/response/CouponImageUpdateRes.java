package org.cubco.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Builder(access = AccessLevel.PRIVATE)
public record CouponImageUpdateRes(
        Long couponId,
        Long cafeId,
        int stampCount,
        String couponImageUrl
) {
    public static CouponImageUpdateRes of(Coupon coupon) {
        return CouponImageUpdateRes.builder()
                .couponId(coupon.getId())
                .cafeId(coupon.getCafe().getId())
                .stampCount(coupon.getCount())
                .couponImageUrl(coupon.getImageUrl())
                .build();
    }
}