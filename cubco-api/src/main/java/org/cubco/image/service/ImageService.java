package org.cubco.image.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.curation.domain.Curation;
import org.cubco.exception.BadRequestException;
import org.cubco.exception.ErrorCode;
import org.cubco.image.domain.CurationImage;
import org.cubco.image.repository.CurationImageRepository;
import org.cubco.s3.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(readOnly = true)
public class ImageService {
    private final CurationImageRepository curationImageRepository;
    private final S3Service s3Service;

    @Transactional
    public String saveCurationImages(List<MultipartFile> images, Curation curation) {
        AtomicInteger sequence = new AtomicInteger();
        Long curationId = curation.getId();
        List<CurationImage> curationImages = images.stream().map(image -> {
            try {
                return CurationImage.create(curation, s3Service.uploadImage("curation/", image, curationId), sequence.getAndIncrement());
            } catch (IOException e) {
                throw new BadRequestException(ErrorCode.INVALID_REQUEST);
            }
        }).toList();
        curationImageRepository.saveAll(curationImages);
        return curationImages.get(0).getImageUrl();
    }
}
