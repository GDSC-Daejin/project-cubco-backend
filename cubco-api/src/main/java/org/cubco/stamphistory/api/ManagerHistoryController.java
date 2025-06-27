package org.cubco.stamphistory.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.MemberStampReq;
import org.cubco.stamphistory.dto.response.ManagerStampListRes;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.cubco.stamphistory.dto.response.StampHistoryDetailRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return CommonResponse.success(HttpStatus.OK, "적립 요청이 완료 되었습니다.", response);
    }

    // MANAGER - 카페로 온 적립요청 리스트조회
    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<Page<ManagerStampListRes>> getAllStampHistory(
            @UserId Long managerId,
            @PathVariable("id") Long cafeId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ManagerStampListRes> stampList = stampHistoryService.getStampListForManager(managerId, cafeId, pageable);
        return CommonResponse.successWithData(HttpStatus.OK, stampList);
    }


    // MANAGER - 적립요청 세부조회
    @GetMapping("/{historyId}")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<StampHistoryDetailRes> getDetailForManager(
            @UserId Long managerId,
            @PathVariable("id") Long historyId
    ) {
        StampHistoryDetailRes response = stampHistoryService.getDetailForManager(historyId, managerId);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }

    // MANAGER - 적립 승인
    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<?> approveStamp(
            @UserId Long managerId,
            @PathVariable("id") Long historyId
    ) {
        stampHistoryService.approveStamp(managerId, historyId);
        return CommonResponse.successWithMessage(HttpStatus.OK, "적립 요청을 승인했습니다.");
    }

    // MANAGER - 적립 거절
    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<?> rejectStamp(
            @UserId Long managerId,
            @PathVariable("id") Long historyId
    ) {
        stampHistoryService.rejectStamp(managerId, historyId);
        return CommonResponse.successWithMessage(HttpStatus.OK,"적립 요청을 거절했습니다.");
    }

}
