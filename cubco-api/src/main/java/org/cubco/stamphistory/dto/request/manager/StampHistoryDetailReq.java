package org.cubco.stamphistory.dto.request.manager;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StampHistoryDetailReq {
    @NotNull
    private Long stampHistoryId;
}
