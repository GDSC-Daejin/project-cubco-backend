package org.cubco.exception;

public class CouponForbiddenException extends CustomException {
    public CouponForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
