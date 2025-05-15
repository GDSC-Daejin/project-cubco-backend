package org.cubco.policy;

import org.cubco.coupon.domain.Coupon;
import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class CouponOwnershipPolicy implements CouponUsePolicy {
    public void validateCouponOwner(Coupon coupon, Long userId) {
        if (!coupon.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.FORBIDDEN); // 403 권한 없음
        }
    }
}
