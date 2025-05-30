package org.cubco.stamphistory.service;

import lombok.RequiredArgsConstructor;
import org.cubco.stamphistory.domain.StampHistory;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.repository.StampHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StampHistoryService {

    private final StampHistoryRepository stampHistoryRepository;

    public void createGuestStampHistory(GuestStampReq request) {
        if (request.getGuestPhone() == null || request.getGuestPhone().isBlank()) {
            throw new IllegalArgumentException("전화번호는 필수입니다.");
        }

        StampHistory history = StampHistory.create()

        stampHistoryRepository.save(history);
    }
}
