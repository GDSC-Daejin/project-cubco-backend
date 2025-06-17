package org.cubco.stamphistory.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberStampRes {
    private Long stampHistoryId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}