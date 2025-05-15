package org.cubco.dto.coupon;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponResponse {

    private Long couponId;
    private String imageUrl;
    private int count;

    public static CouponResponse of(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getImageUrl(),
                coupon.getCount()
        );
    }
}
