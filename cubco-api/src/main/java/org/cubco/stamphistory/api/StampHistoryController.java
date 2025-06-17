package org.cubco.stamphistory.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.dto.request.MemberStampReq;
import org.cubco.stamphistory.dto.response.MemberStampRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stamp-histories")
@RequiredArgsConstructor
public class StampHistoryController implements StampHistoryApiDocs{

    private final StampHistoryService stampHistoryService;

    // 비회원 적립 요청
    @PostMapping("/guest")
    public CommonResponse<?> guestCreate(@RequestBody GuestStampReq request) {
        stampHistoryService.createGuestStampHistory(request);
        return CommonResponse.successWithMessage(HttpStatus.OK,"비회원 적립 요청이 등록되었습니다.");
    }

    // MANAGER - 전화번호 입력 받아 회원 적립 요청 생성
    @PostMapping("/member")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<MemberStampRes> createMemberStamp(@RequestBody @Valid MemberStampReq request) {
        MemberStampRes response = stampHistoryService.createMemberStampRequest(request);
        return CommonResponse.successWithData(HttpStatus.OK, response);
    }

    // MANAGER - 사장님에게 온 적립요청 세부조회
    @GetMapping("/managers/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public CommonResponse<StampHistoryDetailRes> getDetailForManager(
            @UserId Long managerId,
            @PathVariable Long id
    ) {
        return CommonResponse.successWithData(
                stampHistoryService.getDetailForManager(id, managerId)
        );
    }

    // USER - 유저가 요청한 적립 세부조회
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('USER')")
    public CommonResponse<StampHistoryDetailRes> getDetailForUser(
            @UserId Long userId,
            @PathVariable Long id
    ) {
        return CommonResponse.successWithData(
                stampHistoryService.getDetailForUser(id, userId)
        );
    }
}
