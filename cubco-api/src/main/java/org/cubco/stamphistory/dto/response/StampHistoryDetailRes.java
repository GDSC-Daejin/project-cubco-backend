package org.cubco.stamphistory.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StampHistoryDetailRes {
    private Long stampHistoryId;
    private String userName;
    private String cafeName;
    private String cafePhone;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime approvedAt;
}
