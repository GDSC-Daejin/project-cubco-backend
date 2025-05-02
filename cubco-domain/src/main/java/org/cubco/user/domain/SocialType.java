package org.cubco.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SocialType {

    TEST("test"),
    GOOGLE("google"),
    APPLE("apple");

    private final String value;
}