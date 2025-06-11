package org.cubco.exception.qrcode;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class QRTokenInvalidException extends CustomException {
    public QRTokenInvalidException() {
        super(ErrorCode.INVALID_QR_CODE);
    }
}
