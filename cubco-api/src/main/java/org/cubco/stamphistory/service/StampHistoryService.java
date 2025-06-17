package org.cubco.stamphistory.service;

import lombok.RequiredArgsConstructor;
import org.cubco.cafe.domain.Cafe;
import org.cubco.cafe.repository.CafeRepository;
import org.cubco.exception.cafe.CafeNotFoundException;
import org.cubco.exception.user.UserNotFoundException;
import org.cubco.stamphistory.domain.StampHistory;
import org.cubco.stamphistory.domain.StampStatus;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.dto.request.MemberStampReq;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.cubco.stamphistory.repository.StampHistoryRepository;
import org.cubco.user.domain.User;
import org.cubco.user.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class StampHistoryService {

    private final UserRepository userRepository;
    private final StampHistoryRepository stampHistoryRepository;
    private final CafeRepository cafeRepository;
    private final RedisTemplate<String, String> redisTemplate;

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

        return MemberStampRes.builder()
                .stampHistoryId(stampHistory.getId())
                .status(stampHistory.getStatus().name())
                .createdAt(stampHistory.getCreatedAt())
                .modifiedAt(stampHistory.getModifiedAt())
                .build();
    }
}
