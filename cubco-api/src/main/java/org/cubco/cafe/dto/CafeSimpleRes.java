package org.cubco.cafe.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.cafe.domain.Cafe;
import org.cubco.cafe.service.CafeService;

@Builder(access = AccessLevel.PRIVATE)
public record CafeSimpleRes(
        Long cafeId,
        String name,
        String thumbnail
) {
    public static CafeSimpleRes of(Cafe cafe) {
        return CafeSimpleRes.builder()
                .cafeId(cafe.getId())
                .name(cafe.getName())
                .thumbnail(cafe.getThumbnail())
                .build();
    }
}
