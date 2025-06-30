package org.cubco.stamphistory.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.user.MemberStampDetailReq;
import org.cubco.stamphistory.dto.response.ManagerStampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.MemberStampHistoryDetailRes;
import org.cubco.stamphistory.dto.response.MemberStampHistoryListRes;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/user")
@PreAuthorize("hasRole('USER')")
public class UserHistoryController {
    private final StampHistoryService stampHistoryService;

    // USER - 유저의 요청 목록 조회
    @GetMapping("/list")
    public CommonResponse<List<MemberStampHistoryListRes>> getMyList(
            @UserId Long userId
    ) {
        return CommonResponse.successWithData(HttpStatus.OK,
                stampHistoryService.getListForUser(userId)
        );
    }

    // USER - 유저가 요청한 적립 세부조회
    @GetMapping("/detail")
    public CommonResponse<MemberStampHistoryDetailRes> getDetail(
            @UserId Long userId,
            @RequestBody @Valid MemberStampDetailReq memberStampDetailReq
    ) {
        return CommonResponse.successWithData(HttpStatus.OK,
                stampHistoryService.getDetailForUser(memberStampDetailReq.getStampHistoryId(), userId)
        );
    }
}
