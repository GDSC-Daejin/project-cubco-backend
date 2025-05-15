package org.cubco.policy;

import org.cubco.coupon.domain.Coupon;

public interface CouponUsePolicy {
    void validateCouponCount(Coupon coupon, int count);
    void validateCouponOwner(Coupon coupon, Long userId);
}
