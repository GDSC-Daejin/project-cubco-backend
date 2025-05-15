package org.cubco.exception;

public class CouponForbiddenException extends CustomException {
    public CouponForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN);
    }
}
