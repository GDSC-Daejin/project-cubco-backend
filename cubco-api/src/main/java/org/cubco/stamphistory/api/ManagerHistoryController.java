package org.cubco.stamphistory.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.MemberStampReq;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.cubco.stamphistory.dto.response.StampHistoryDetailRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerHistoryController {
    private final StampHistoryService stampHistoryService;

    // MANAGER - 전화번호 입력 받아 회원 적립 요청 생성
    @PostMapping("/member")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<MemberStampRes> createMemberStamp(@RequestBody @Valid MemberStampReq request) {
        MemberStampRes response = stampHistoryService.createMemberStampRequest(request);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }

    // MANAGER - 사장님에게 온 적립요청 세부조회
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<StampHistoryDetailRes> getDetailForManager(
            @UserId Long managerId,
            @PathVariable Long historyId
    ) {
        StampHistoryDetailRes response = stampHistoryService.getDetailForManager(historyId, managerId);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }
}
