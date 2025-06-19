package org.cubco.coupon.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record CouponRes(
        Long couponId,
        Long cafeId,
        int stampCount,
        String couponImageUrl,
        LocalDateTime lastUpdated
) {
    public static CouponRes of(Coupon coupon) {
        return CouponRes.builder()
                .couponId(coupon.getId())
                .cafeId(coupon.getCafe().getId())
                .stampCount(coupon.getCount())
                .couponImageUrl(coupon.getImageUrl())
                .lastUpdated(coupon.getModifiedAt())
                .build();
    }
}
