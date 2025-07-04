package org.cubco.stamphistory.repository;

import org.cubco.stamphistory.domain.StampHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StampHistoryRepository extends JpaRepository<StampHistory, Long> {
    List<StampHistory> findByUserIdOrderByCreatedAtDesc(Long userId);
    Page<StampHistory> findByCafeIdOrderByCreatedAtDesc(Long cafeId, Pageable pageable);
}
