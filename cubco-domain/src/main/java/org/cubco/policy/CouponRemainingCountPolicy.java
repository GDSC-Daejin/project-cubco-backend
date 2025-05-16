package org.cubco.policy;

import org.cubco.coupon.domain.Coupon;
import org.cubco.exception.CouponInsufficientCountException;
import org.springframework.stereotype.Component;

@Component
public class CouponRemainingCountPolicy {
    public void validateCouponCount(Coupon coupon, int count) {
        if (coupon.getCount() < count) {
            throw new CouponInsufficientCountException();
        }
    }
}