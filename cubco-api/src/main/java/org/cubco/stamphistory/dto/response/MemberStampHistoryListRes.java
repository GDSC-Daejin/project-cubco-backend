package org.cubco.stamphistory.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record MemberStampHistoryListRes(
        Long historyId,
        String cafeName,
        String status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime approvedAt
) {
    public static MemberStampHistoryListRes of(StampHistory stampHistory) {
        return MemberStampHistoryListRes.builder()
                .historyId(stampHistory.getId())
                .cafeName(stampHistory.getCafe().getName())
                .status(stampHistory.getStatus().toString())
                .createdAt(stampHistory.getCreatedAt())
                .modifiedAt(stampHistory.getModifiedAt())
                .approvedAt(stampHistory.getApprovedAt())
                .build();
    }
}