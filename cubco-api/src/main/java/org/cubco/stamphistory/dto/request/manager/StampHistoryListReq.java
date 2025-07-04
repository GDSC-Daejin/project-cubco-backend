package org.cubco.stamphistory.dto.request.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "적립 요청 리스트 조회 요청 데이터")
public class StampHistoryListReq {
    @NotNull
    @Schema(description = "적립 요청을 조회할 카페 ID", example = "1")
    private Long cafeId;
}
