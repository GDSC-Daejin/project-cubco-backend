package org.cubco.curation.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cubco.curation.domain.Curation;
import org.cubco.exception.EntityNotFoundException;
import org.cubco.exception.ErrorCode;
import org.cubco.image.service.ImageService;
import org.cubco.curation.dto.request.TagCreateReq;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(readOnly = true)
public class AsyncService {
    private final ImageService imageService;
    private final CurationTagService curationTagService;

    @Transactional
    public String createCurationImage(List<MultipartFile> images, Curation curation) {
        if (images.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.IMAGE_NOT_FOUND);
        }
        return imageService.saveCurationImages(images, curation);
    }

    @Async
    public void createCurationTags(List<TagCreateReq> tags, Curation curation) {
        if (tags.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND);
        }
        curationTagService.createCurationTag(tags, curation);
    }
}
