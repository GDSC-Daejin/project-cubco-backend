package org.cubco.exception.cafe;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class CafeForbiddenException extends CustomException {
    public CafeForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}