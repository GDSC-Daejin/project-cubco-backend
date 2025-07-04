package org.cubco.exception.coupon;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class CouponForbiddenException extends CustomException {
    public CouponForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
