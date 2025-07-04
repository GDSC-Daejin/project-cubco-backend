package org.cubco.stamphistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.cubco.stamphistory.domain.StampHistory;

import java.time.LocalDateTime;


@Builder(access= AccessLevel.PRIVATE)
@Schema(description = "관리자용 적립 요청 리스트 응답 데이터")
public record ManagerStampListRes (

        @Schema(description = "적립 요청 ID", example = "123")
        Long historyId,

        @Schema(description = "요청한 회원 이름", example = "홍길동")
        String userName,

        @Schema(description = "요청한 회원 전화번호", example = "01012345678")
        String userPhone,

        @Schema(description = "적립 상태 (PENDING/ACCEPTED/REJECTED)", example = "PENDING")
        String status,

        @Schema(description = "적립 요청 생성일시", example = "2024-07-01T15:00:00")
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