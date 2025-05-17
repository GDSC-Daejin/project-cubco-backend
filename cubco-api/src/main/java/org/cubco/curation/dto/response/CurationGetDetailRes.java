package org.cubco.curation.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.cubco.curation.domain.Curation;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record CurationGetDetailRes(
        Long curationId,
        List<ImageList> images,
        List<CurationTag> tags,
        String title,
        String content,
        int reportCount,
        int likeCount,
        boolean isUserLiked
) {
    public static CurationGetDetailRes of(Curation curation,
                                          List<ImageList> images,
                                          List<CurationTag> tags,
                                          int likeCount,
                                          boolean isUserLiked) {
        return CurationGetDetailRes.builder()
                .curationId(curation.getId())
                .images(images)
                .tags(tags)
                .title(curation.getTitle())
                .content(curation.getContent())
                .reportCount(curation.getReportCount())
                .likeCount(likeCount)
                .isUserLiked(isUserLiked)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public record ImageList(
            String imageUrl,
            int sortOrder
    ) {
        public static ImageList of(String imageUrl, int sortOrder) {
            return ImageList.builder()
                    .imageUrl(imageUrl)
                    .sortOrder(sortOrder)
                    .build();
        }
    }

    @Builder(access = AccessLevel.PRIVATE)
    public record CurationTag(
            String name
    ) {
        public static CurationTag of(String name) {
            return CurationTag.builder()
                    .name(name)
                    .build();
        }
    }
}