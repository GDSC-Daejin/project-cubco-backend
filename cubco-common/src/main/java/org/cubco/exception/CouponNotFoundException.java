package org.cubco.exception;

public class CouponNotFoundException extends CustomException {
    public CouponNotFoundException() {
      super(ErrorCode.COUPON_NOT_FOUND);
    }
}
