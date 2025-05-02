package org.cubco.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleType {

    MEMBER("member"),
    ADMIN("admin"),
    MANAGER("manager");

    private final String value;
}
