package org.cubco.stamphistory.repository;

import org.cubco.stamphistory.domain.StampHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StampHistoryRepository extends JpaRepository<StampHistory, Long> {

    // 비회원이 오늘 이미 적립 요청한 기록이 있는지 확인
    boolean existsByGuestPhoneAndCafeIdAndCreatedAtBetween(
            String guestPhone,
            Long cafeId,
            LocalDate startOfDay,
            LocalDate endOfDay
    );

    // 오늘 가장 먼저 들어온 비회원 적립 요청
    Optional<StampHistory> findFirstByGuestPhoneAndCafeIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            String guestPhone,
            Long cafeId,
            LocalDate startOfDay,
            LocalDate endOfDay
    );
}
