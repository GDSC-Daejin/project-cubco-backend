package org.cubco.exception;

public class CouponInsufficientCountException extends CustomException {
    public CouponInsufficientCountException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
