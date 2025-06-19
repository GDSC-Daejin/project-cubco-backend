package org.cubco.stamphistory.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;


@Builder(access= AccessLevel.PRIVATE)
public record ManagerStampListRes (
        Long historyId,
        String userName,
        String userPhone,
        String status,
        LocalDateTime createdAt
) {
    public static ManagerStampListRes of(StampHistory stampHistory) {
        return ManagerStampListRes.builder()
                .historyId(stampHistory.getId())
                .userName(stampHistory.getUser().getName())
                .userPhone(stampHistory.getUser().getPhone())
                .status(stampHistory.getStatus().toString())
                .createdAt(stampHistory.getCreatedAt())
                .build();
    }
}