package org.cubco.exception.stamphistory;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class HistoryAlreadyProcessedException extends CustomException {
    public HistoryAlreadyProcessedException() {
        super(ErrorCode.STAMP_HISTORY_ALREADY_PROCESSED);
    }
}