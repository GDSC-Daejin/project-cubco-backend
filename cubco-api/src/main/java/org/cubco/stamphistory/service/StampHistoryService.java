package org.cubco.stamphistory.service;

import lombok.RequiredArgsConstructor;
import org.cubco.cafe.domain.Cafe;
import org.cubco.cafe.repository.CafeRepository;
import org.cubco.cafe.service.CafeService;
import org.cubco.exception.cafe.CafeForbiddenException;
import org.cubco.exception.cafe.CafeNotFoundException;
import org.cubco.exception.stamphistory.HistoryAlreadyProcessedException;
import org.cubco.exception.stamphistory.HistoryForbiddenException;
import org.cubco.exception.stamphistory.HistoryNotFoundException;
import org.cubco.exception.user.UserNotFoundException;
import org.cubco.stamphistory.domain.StampHistory;
import org.cubco.stamphistory.domain.StampStatus;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.dto.request.MemberStampReq;
import org.cubco.stamphistory.dto.response.ManagerStampListRes;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.cubco.stamphistory.dto.response.StampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.StampHistoryListRes;
import org.cubco.stamphistory.repository.StampHistoryRepository;
import org.cubco.tag.domain.CafeTag;
import org.cubco.user.domain.User;
import org.cubco.user.repository.UserRepository;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StampHistoryService {

    private final UserRepository userRepository;
    private final StampHistoryRepository stampHistoryRepository;
    private final CafeRepository cafeRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final CafeService cafeService;

    // 비회원 적립요청 생성
    public void createGuestStampHistory(GuestStampReq request) {
        User user = User.createGuest(request.getPhone());
        Cafe cafe = cafeRepository.findById(request.getCafeId()).orElseThrow(CafeNotFoundException::new);

        // 적립
        StampHistory history = StampHistory.create(user, cafe, StampStatus.PENDING);
        stampHistoryRepository.save(history);

        // QR 사용 처리
        redisTemplate.opsForValue().set("USED_QR_KEY:" + request.getQrKey(), "USED", Duration.ofMinutes(3));
    }

    // 회원 적립요청 생성
    public MemberStampRes createMemberStampRequest(MemberStampReq request) {
        // 1. 전화번호로 유저 조회
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(UserNotFoundException::new);

        // 2. 카페 조회
        Cafe cafe = cafeRepository.findById(request.getCafeId())
                .orElseThrow(CafeNotFoundException::new);

        // 3. 적립 요청 생성
        StampHistory history = StampHistory.create(user, cafe, StampStatus.PENDING);
        StampHistory stampHistory = stampHistoryRepository.save(history);

        return MemberStampRes.of(stampHistory);
    }

    // USER - 적립요청 목록 조회
    public List<StampHistoryListRes> getListForUser(Long userId) {
        return stampHistoryRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(StampHistoryListRes::of)
                .toList();
    }

    // USER - 적립요청 세부조회
    public StampHistoryDetailRes getDetailForUser(Long stampHistoryId, Long userId) {
        // 1. Id에 맞는 요청이 있는지 확인
        StampHistory stampHistory = stampHistoryRepository.findById(stampHistoryId)
                .orElseThrow(HistoryNotFoundException::new);

        // 2. 요청이 본인이 요청한 것인지 확인
        if (!stampHistory.getUser().getId().equals(userId)) {
            throw new HistoryForbiddenException(); // 본인 소유 아님
        }

        return StampHistoryDetailRes.of(stampHistory);
    }

    // MANAGER - 적립요청 리스트 조회
    public Page<ManagerStampListRes> getStampListForManager(Long managerId, Long cafeId, Pageable pageable) {
        cafeService.verifiedCafeForManager(managerId, cafeId);
        return stampHistoryRepository.findByCafeIdOrderByCreatedAtDesc(cafeId, pageable)
                .map(ManagerStampListRes::of);
    }

    // MANAGER - 적립요청 세부조회
    public StampHistoryDetailRes getDetailForManager(Long stampHistoryId, Long managerId) {
        // 세부조회 권한이 있는지 확인
        StampHistory history = getVerifiedStampForManager(managerId, stampHistoryId);

        return StampHistoryDetailRes.of(history);
    }

    // MANAGER - 적립 승인
    @Transactional
    public void approveStamp(Long managerId, Long stampHistoryId) {
        // 요청 수정 권한이 있는지 확인
        StampHistory history = getVerifiedStampForManager(managerId, stampHistoryId);

        if (history.getStatus() != StampStatus.PENDING) {
            throw new HistoryAlreadyProcessedException();
        }

        history.approve(); // 상태 변경 + 승인시간 설정
    }

    // MANAGER - 적립 거절
    @Transactional
    public void rejectStamp(Long managerId, Long stampHistoryId) {
        // 요청 수정 권한이 있는지 확인
        StampHistory history = getVerifiedStampForManager(managerId, stampHistoryId);

        if (history.getStatus() != StampStatus.PENDING) {
            throw new HistoryAlreadyProcessedException();
        }

        history.reject(); // 상태 변경 + 승인시간 설정
    }

    // MANAGER - 사장님 인증 ( managerId, stampHistoryId 기반)
    private StampHistory getVerifiedStampForManager(Long managerId, Long stampHistoryId) {
        StampHistory history = stampHistoryRepository.findById(stampHistoryId)
                .orElseThrow(HistoryNotFoundException::new);

        // StampHistory의 ManagerId와 로그인한 유저의 Id가 같은지 비교
        if (!history.getCafe().getManagerId().equals(managerId)) {
            throw new HistoryForbiddenException();
        }

        return history;
    }


}
