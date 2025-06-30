package org.cubco.stamphistory.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberStampDetailReq {
    @NotNull
    private Long stampHistoryId;
}
