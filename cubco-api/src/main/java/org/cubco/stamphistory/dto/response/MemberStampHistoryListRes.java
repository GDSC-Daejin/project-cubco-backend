package org.cubco.stamphistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "회원 적립 요청 리스트 응답 데이터")
public record MemberStampHistoryListRes(
        @Schema(description = "적립 요청 ID", example = "123")
        Long historyId,

        @Schema(description = "카페명", example = "카페선샤인")
        String cafeName,

        @Schema(description = "적립 상태 (PENDING/ACCEPTED/REJECTED)", example = "PENDING")
        String status,

        @Schema(description = "적립 요청 생성일시", example = "2024-07-01T15:00:00")
        LocalDateTime createdAt,

        @Schema(description = "적립 요청 수정일시", example = "2024-07-01T15:10:00")
        LocalDateTime modifiedAt,

        @Schema(description = "적립 승인일시(최초 생성 시 null, 승인 or 거절 시 날짜표시")
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