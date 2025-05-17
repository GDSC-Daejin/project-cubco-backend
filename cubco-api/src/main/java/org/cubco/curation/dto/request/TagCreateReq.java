package org.cubco.curation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class TagCreateReq {
    @Schema(description = "큐레이션 태그", example = "데이트")
    private String name;
}
