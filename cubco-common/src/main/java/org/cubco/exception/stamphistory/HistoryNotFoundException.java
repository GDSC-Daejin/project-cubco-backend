package org.cubco.exception.stamphistory;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class HistoryNotFoundException extends CustomException {
    public HistoryNotFoundException() {
        super(ErrorCode.HISTORY_NOT_FOUND);
    }
}