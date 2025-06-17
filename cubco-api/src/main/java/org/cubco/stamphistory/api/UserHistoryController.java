package org.cubco.stamphistory.api;

import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.response.StampHistoryDetailRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/user")
@PreAuthorize("hasRole('USER')")
public class UserHistoryController {
    private final StampHistoryService stampHistoryService;

    // USER - 유저가 요청한 적립 세부조회
    @GetMapping("/{historyId}")
    public CommonResponse<StampHistoryDetailRes> getDetail(@UserId Long userId, @PathVariable Long historyId) {
        return CommonResponse.successWithData(
                stampHistoryService.getDetailForUser(historyId, userId)
        );
    }

    @GetMapping("/my")
    public CommonResponse<List<StampHistoryListRes>> getMyList(@UserId Long userId) {
        return CommonResponse.successWithData(
                stampHistoryService.getStampListForUser(userId)
        );
    }
}
