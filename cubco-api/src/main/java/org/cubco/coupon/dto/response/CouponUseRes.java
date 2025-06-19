package org.cubco.coupon.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Builder(access = AccessLevel.PRIVATE)
public record CouponUseRes(
        Long couponId,
        Long cafeId,
        int stampCount,
        String couponImageUrl
) {
    public static CouponUseRes of(Coupon coupon) {
        return CouponUseRes.builder()
                .couponId(coupon.getId())
                .cafeId(coupon.getCafe().getId())
                .stampCount(coupon.getCount())
                .couponImageUrl(coupon.getImageUrl())
                .build();
    }
}
