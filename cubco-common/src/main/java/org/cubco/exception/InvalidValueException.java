package org.cubco.exception;

public class InvalidValueException extends CustomException {
    public InvalidValueException() {
        super(ErrorCode.INVALID_REQUEST);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
