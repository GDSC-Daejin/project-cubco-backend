package org.cubco.dto.coupon;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponUseRequest {

    @Min(1)
    @Max(10)
    private int count;

    public static CouponUseRequest of(Coupon coupon) {
        return new CouponUseRequest(
                coupon.getCount()
        );
    }

}
