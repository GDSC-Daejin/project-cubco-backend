package org.cubco.policy;

import org.cubco.coupon.domain.Coupon;
import org.cubco.exception.CouponForbiddenException;
import org.springframework.stereotype.Component;

@Component
public class CouponOwnershipPolicy {
    public void validateCouponOwner(Coupon coupon, Long userId) {
        if (!coupon.getUser().getId().equals(userId)) {
            throw new CouponForbiddenException(); // 403 권한 없음
        }
    }
}
