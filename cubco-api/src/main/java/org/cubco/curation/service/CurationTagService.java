package org.cubco.curation.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cubco.curation.domain.Curation;
import org.cubco.tag.domain.CurationTag;
import org.cubco.curation.dto.request.TagCreateReq;
import org.cubco.tag.repository.CurationTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CurationTagService {
    private final CurationTagRepository curationTagRepository;

    @Transactional
    public void createCurationTag(List<TagCreateReq> tags, Curation curation) {
        List<CurationTag> curationTags = tags.stream()
                .map(tag -> CurationTag.create(curation, tag.getName()))
                .toList();
        curationTagRepository.saveAll(curationTags);
    }
}
