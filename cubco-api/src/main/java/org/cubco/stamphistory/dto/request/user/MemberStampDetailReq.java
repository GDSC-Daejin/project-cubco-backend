package org.cubco.stamphistory.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "회원 적립 요청 상세 조회 요청 데이터")
public class MemberStampDetailReq {
    @NotNull
    @Schema(description = "조회할 적립 요청 ID", example = "123")
    private Long stampHistoryId;
}
