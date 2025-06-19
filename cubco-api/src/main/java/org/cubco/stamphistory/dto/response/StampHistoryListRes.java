package org.cubco.stamphistory.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record StampHistoryListRes(
        Long historyId,
        String cafeName,
        String status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime approvedAt
) {
    public static StampHistoryListRes of(StampHistory stampHistory) {
        return StampHistoryListRes.builder()
                .historyId(stampHistory.getId())
                .cafeName(stampHistory.getCafe().getName())
                .status(stampHistory.getStatus().toString())
                .createdAt(stampHistory.getCreatedAt())
                .modifiedAt(stampHistory.getModifiedAt())
                .approvedAt(stampHistory.getApprovedAt())
                .build();
    }
}