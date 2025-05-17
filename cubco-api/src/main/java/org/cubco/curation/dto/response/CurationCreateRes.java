package org.cubco.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PROTECTED)
public record CurationCreateRes(
        Long curationId
) {
    public static CurationCreateRes of(Long curationId) {
        return CurationCreateRes.builder()
                .curationId(curationId)
                .build();
    }
}