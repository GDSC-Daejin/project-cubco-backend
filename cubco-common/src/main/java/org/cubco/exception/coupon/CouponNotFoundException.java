package org.cubco.exception.coupon;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class CouponNotFoundException extends CustomException {
    public CouponNotFoundException() {
      super(ErrorCode.COUPON_NOT_FOUND);
    }
}
