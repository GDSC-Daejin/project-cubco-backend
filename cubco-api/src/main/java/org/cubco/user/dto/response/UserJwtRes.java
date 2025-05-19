package org.cubco.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserJwtRes(
        Long userId,
        String accessToken
) {
    public static UserJwtRes of(Long userId, String accessToken) {
        return UserJwtRes.builder()
                .userId(userId)
                .accessToken(accessToken)
                .build();
    }
}