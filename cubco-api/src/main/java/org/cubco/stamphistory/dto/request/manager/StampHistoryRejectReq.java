package org.cubco.stamphistory.dto.request.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "적립 요청 거절 요청 데이터")
public class StampHistoryRejectReq {
    @NotNull
    @Schema(description = "거절할 적립 요청 ID", example = "123")
    private Long stampHistoryId;
}
