package org.cubco.cafe.service;

import lombok.RequiredArgsConstructor;
import org.cubco.cafe.domain.Cafe;
import org.cubco.cafe.dto.CafeSimpleRes;
import org.cubco.cafe.repository.CafeRepository;
import org.cubco.exception.cafe.CafeForbiddenException;
import org.cubco.exception.cafe.CafeNotFoundException;
import org.cubco.exception.stamphistory.HistoryForbiddenException;
import org.cubco.stamphistory.domain.StampHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;

    // MANAGER - 보유한 카페 리스트 조회
    public List<CafeSimpleRes> getCafesByManager(Long managerId) {
        return cafeRepository.findAllByManagerId(managerId)
                .stream()
                .map(CafeSimpleRes::of)
                .toList();
    }

    // MANAGER - 사장님 인증 ( managerId, cafeId 기반)
    public boolean verifiedCafeForManager(Long managerId, Long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFoundException::new);

        // Cafe의 ManagerId와 로그인한 유저의 Id가 같은지 비교
        if (!cafe.getManagerId().equals(managerId)) {
            throw new CafeForbiddenException();
        }

        return true;
    }
}
