package org.cubco.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.curation.domain.Curation;

@Builder(access = AccessLevel.PRIVATE)
public record CurationDtoGetRes(
        Long curationId,
        String thumbnail,
        String title,
        String content,
        int like
) {
    public static CurationDtoGetRes of(Curation curation,
                                       int like) {
        return CurationDtoGetRes.builder()
                .curationId(curation.getId())
                .thumbnail(curation.getThumbnail())
                .title(curation.getTitle())
                .content(curation.getContent())
                .like(like)
                .build();
    }
}