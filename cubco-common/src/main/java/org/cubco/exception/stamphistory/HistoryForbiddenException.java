package org.cubco.exception.stamphistory;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class HistoryForbiddenException extends CustomException {
    public HistoryForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}