package org.cubco.exception.user;

import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}