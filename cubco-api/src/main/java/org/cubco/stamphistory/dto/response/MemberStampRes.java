package org.cubco.stamphistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@Schema(description = "회원 적립 요청 생성 후 응답 데이터")
public record MemberStampRes(

        @Schema(description = "적립 요청 ID", example = "123")
        Long historyId,

        @Schema(description = "적립 상태 (PENDING/ACCEPTED/REJECTED)", example = "PENDING")
        String status,

        @Schema(description = "적립 요청 생성일시", example = "2024-07-01T15:00:00")
        LocalDateTime createdAt,

        @Schema(description = "적립 요청 수정일시", example = "2024-07-01T15:10:00")
        LocalDateTime modifiedAt
) {
    public static MemberStampRes of(StampHistory stampHistory) {
        return MemberStampRes.builder()
                .historyId(stampHistory.getId())
                .status(stampHistory.getStatus().toString())
                .createdAt(stampHistory.getCreatedAt())
                .modifiedAt(stampHistory.getModifiedAt())
                .build();
    }
}