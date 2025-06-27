package org.cubco.stamphistory.api;

import lombok.RequiredArgsConstructor;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.user.GuestStampReq;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stamp-histories/guest")
public class GuestHistoryController {
    private final StampHistoryService stampHistoryService;

    // 비회원 적립 요청
    @PostMapping
    public CommonResponse<?> guestCreate(@RequestBody GuestStampReq request) {
        stampHistoryService.createGuestStampHistory(request);
        return CommonResponse.successWithMessage(HttpStatus.OK,"비회원 적립 요청이 등록되었습니다.");
    }

}
