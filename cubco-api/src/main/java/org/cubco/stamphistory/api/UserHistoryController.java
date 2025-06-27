package org.cubco.stamphistory.api;

import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.response.StampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.StampHistoryListRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/user")
@PreAuthorize("hasRole('USER')")
public class UserHistoryController {
    private final StampHistoryService stampHistoryService;

    // USER - 유저가 요청한 적립 세부조회
    @GetMapping("/{historyId}")
    public CommonResponse<StampHistoryDetailRes> getDetail(@UserId Long userId, @PathVariable("historyId") Long historyId) {
        return CommonResponse.successWithData(HttpStatus.OK,
                stampHistoryService.getDetailForUser(historyId, userId)
        );
    }

    // USER - 유저의 요청 목록 조회
    @GetMapping("/my")
    public CommonResponse<List<StampHistoryListRes>> getMyList(
            @UserId Long userId
    ) {
        return CommonResponse.successWithData(HttpStatus.OK,
                stampHistoryService.getListForUser(userId)
        );
    }
}
