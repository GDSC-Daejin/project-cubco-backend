package org.cubco.exception;

public class BadRequestException extends CustomException {
    public BadRequestException() {
        super(ErrorCode.INVALID_REQUEST);
    }
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
