package org.cubco.stamphistory.api;

import lombok.RequiredArgsConstructor;
import org.cubco.response.CommonResponse;
import org.cubco.stamphistory.dto.request.GuestStampReq;
import org.cubco.stamphistory.service.StampHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stamp-histories")
@RequiredArgsConstructor
public class StampHistoryController {

    private final StampHistoryService stampHistoryService;

    // 비회원 적립 요청
    @PostMapping("/guest")
    public CommonResponse<?> guestCreate(@RequestBody GuestStampReq request) {
        stampHistoryService.createGuestStampHistory(request);
        return CommonResponse.successWithMessage(HttpStatus.OK,"비회원 적립 요청이 등록되었습니다.");
    }
}
