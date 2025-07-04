package org.cubco.stamphistory.dto.request.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "적립 요청 승인 요청 데이터")
public class StampHistoryApproveReq {
    @NotNull
    @Schema(description = "승인할 적립 요청 ID", example = "123")
    private Long stampHistoryId;
}
