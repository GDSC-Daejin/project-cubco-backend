package org.cubco.exception.cafe;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class CafeNotFoundException extends CustomException {
    public CafeNotFoundException() {
        super(ErrorCode.CAFE_NOT_FOUND);
    }
}
