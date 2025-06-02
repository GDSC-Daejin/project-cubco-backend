package org.cubco.coupon.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CouponResponse {

    private Long couponId;
    private Long cafeId;
    private int stampCount;
    private String couponImageUrl;
    private LocalDateTime lastUpdated;

    public static CouponResponse of(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getCafe().getId(),
                coupon.getCount(),
                coupon.getImageUrl(),
                coupon.getModifiedAt()
        );
    }
}
