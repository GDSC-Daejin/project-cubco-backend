package org.cubco.exception.qrcode;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class QRAlreadyUsedException extends CustomException {
    public QRAlreadyUsedException() {
        super(ErrorCode.QR_ALREADY_USED);
    }
}
