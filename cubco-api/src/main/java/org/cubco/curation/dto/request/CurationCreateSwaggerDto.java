package org.cubco.curation.dto.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record CurationCreateSwaggerDto(
        CurationCreateReq curation,
        List<TagCreateReq> tags,
        List<MultipartFile> images
) {}