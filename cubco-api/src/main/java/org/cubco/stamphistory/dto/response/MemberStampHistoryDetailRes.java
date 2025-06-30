package org.cubco.stamphistory.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record MemberStampHistoryDetailRes(
        Long stampHistoryId,
        String userName,
        String cafeName,
        String cafePhone,
        String status,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        LocalDateTime approvedAt
) {
    public static MemberStampHistoryDetailRes of(StampHistory stampHistory) {
        return MemberStampHistoryDetailRes.builder()
                .stampHistoryId(stampHistory.getId())
                .userName(stampHistory.getUser().getName())
                .cafeName(stampHistory.getCafe().getName())
                .status(stampHistory.getStatus().toString())
                .createdAt(stampHistory.getCreatedAt())
                .modifiedAt(stampHistory.getModifiedAt())
                .approvedAt(stampHistory.getApprovedAt())
                .build();
    }
}
