package org.cubco.curation.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cubco.curation.domain.Curation;
import org.cubco.curation.dto.request.CurationCreateReq;
import org.cubco.curation.dto.response.CurationCreateRes;
import org.cubco.curation.dto.response.CurationGetDetailRes;
import org.cubco.curation.repository.CurationRepository;
import org.cubco.exception.EntityNotFoundException;
import org.cubco.exception.ErrorCode;
import org.cubco.exception.ForbiddenException;
import org.cubco.image.domain.CurationImage;
import org.cubco.image.repository.CurationImageRepository;
import org.cubco.like.domain.Like;
import org.cubco.like.repository.LikeRepository;
import org.cubco.tag.domain.CurationTag;
import org.cubco.tag.repository.CurationTagRepository;
import org.cubco.user.domain.User;
import org.cubco.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(readOnly = true)
public class CurationService {
    private final CurationRepository curationRepository;
    private final CurationImageRepository curationImageRepository;
    private final CurationTagRepository curationTagRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final AsyncService asyncService;

    @Transactional
    public CurationCreateRes createCuration(Long userId, CurationCreateReq curationCreateReq, List<MultipartFile> images) {
        User user = getUser(userId);
        Curation curation = Curation.create(user, curationCreateReq.getTitle(), curationCreateReq.getContent());
        Curation saveCuration = curationRepository.save(curation);
        asyncService.createCurationImage(images, saveCuration);
        asyncService.createCurationTags(curationCreateReq.getTags(), saveCuration);
        return CurationCreateRes.of(saveCuration.getId());
    }

    @Transactional
    public void createCurationLike(Long userId, Long curationId) {
        User user = getUser(userId);
        Curation curation = getCuration(curationId);
        Like like = Like.create(user, curation);
        likeRepository.save(like);
    }

    @Transactional
    public void deleteCurationLike(Long userId, Long curationId) {
        User user = getUser(userId);
        Curation curation = getCuration(curationId);
        Like like = getLike(user, curation);
        likeRepository.delete(like);
    }

    public CurationGetDetailRes getCurationDetail(Long userId, Long curationId) {
        User user = getUser(userId);
        Curation curation = getCuration(curationId);
        List<CurationImage> images = getCurationImages(curation);
        validateImage(images);

        List<CurationGetDetailRes.ImageList> imagesRes = images.stream().map(
                imageList -> CurationGetDetailRes.ImageList.of(
                        imageList.getImageUrl(),
                        imageList.getSortOrder()
                )).toList();

        List<CurationTag> tags = getCurationTags(curation);
        validateCurationTag(tags);
        List<CurationGetDetailRes.CurationTag> tagsRes = tags.stream().map(
                tagList -> CurationGetDetailRes.CurationTag.of(
                        tagList.getName()
                )).toList();

        int likeCount = getLikeCount(curation);
        boolean isMyCuration = isAuthor(user, curation.getId());
        boolean isUserLiked = false;
        if (!isMyCuration) {
            isUserLiked = hasUserLikedCuration(user, curation);
        }
        return CurationGetDetailRes.of(curation, imagesRes, tagsRes, likeCount, isUserLiked);
    }

    @Transactional
    public void deleteCuration(Long userId, Long curationId) {
        User user = getUser(userId);
        Curation curation = getCuration(curationId);
        validateCuration(user, curation);
        likeRepository.deleteByCuration(curation);
        curationTagRepository.deleteByCuration(curation);
        curationImageRepository.deleteByCuration(curation);
        curationRepository.delete(curation);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private Curation getCuration(Long curationId) {
        return curationRepository.findById(curationId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CURATION_NOT_FOUND));
    }

    private List<CurationImage> getCurationImages(Curation curation) {
        return curationImageRepository.findAllByCuration(curation);
    }

    private Like getLike(User user, Curation curation) {
        return likeRepository.findLikeByUserAndCuration(user, curation)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.LIKE_NOT_FOUND));
    }

    private void validateImage(List<CurationImage> images) {
        if (images.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.IMAGE_NOT_FOUND);
        }
    }

    private List<CurationTag> getCurationTags(Curation curation) {
        return curationTagRepository.findAllByCuration(curation);
    }

    private void validateCurationTag(List<CurationTag> curationTags) {
        if (curationTags.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND);
        }
    }

    private void validateCuration(User user, Curation curation) {
        if (!user.equals(curation.getUser())) {
            throw new ForbiddenException(ErrorCode.CURATION_DELETE_ACCESS_DENIED);
        }
    }

    private int getLikeCount(Curation curation) {
        return likeRepository.countByCuration(curation);
    }

    private boolean isAuthor(User user, Long curationId) {
        return curationRepository.existsByUserAndId(user, curationId);
    }

    private boolean hasUserLikedCuration(User user, Curation curation) {
        return likeRepository.existsLikeByUserAndCuration(user, curation);
    }
}
