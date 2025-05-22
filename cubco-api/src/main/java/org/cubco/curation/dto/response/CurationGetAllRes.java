package org.cubco.curation.dto.response;

import lombok.*;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record CurationGetAllRes(
        List<CurationDtoGetRes> curations,
        int totalPages,
        long totalElements
) {
    public static CurationGetAllRes of(List<CurationDtoGetRes> curations, int totalPages, long totalElements) {
        return CurationGetAllRes.builder()
                .curations(curations)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }
}