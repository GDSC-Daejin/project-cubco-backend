package org.cubco.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {

    ACTIVE("active"),
    INACTIVE("inactive"),
    DELETED("deleted");

    private final String value;
}