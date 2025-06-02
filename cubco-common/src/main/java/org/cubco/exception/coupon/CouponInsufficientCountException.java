package org.cubco.exception.coupon;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class CouponInsufficientCountException extends CustomException {
    public CouponInsufficientCountException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
