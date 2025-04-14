package org.cubco.stamphistory.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum StampStatus {
    ACCEPTED("승인"),
    REJECTED("거부"),
    PENDING("보류");

    private final String value;
}
