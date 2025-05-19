package org.cubco.curation.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class CurationCreateReq {
    @Size(min = 5)
    String title;

    @Size(min = 10)
    String content;

    @Size(min = 1, max = 5)
    List<TagCreateReq> tags;
}
