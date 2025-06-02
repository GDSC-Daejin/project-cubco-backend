package org.cubco.stamphistory.service;

import lombok.RequiredArgsConstructor;
import org.cubco.cafe.domain.Cafe;
import org.cubco.cafe.repository.CafeRepository;
import org.cubco.exception.cafe.CafeNotFoundException;
import org.cubco.stamphistory.domain.StampHistory;
import org.cubco.stamphistory.domain.StampStatus;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.repository.StampHistoryRepository;
import org.cubco.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StampHistoryService {

    private final StampHistoryRepository stampHistoryRepository;
    private final CafeRepository cafeRepository;

    // 비회원 적립요청 생성
    public void createGuestStampHistory(GuestStampReq request) {

        User user = User.createGuest(request.getPhone());
        Cafe cafe = cafeRepository.findById(request.getCafeId()).orElseThrow(()
                -> new CafeNotFoundException());

        StampHistory cafegistory = StampHistory.create(
                user,
                cafe,
                StampStatus.PENDING
        );

        stampHistoryRepository.save(cafegistory);
    }
}
