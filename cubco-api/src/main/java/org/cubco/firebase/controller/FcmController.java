package org.cubco.firebase.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cubco.auth.resolver.UserId;
import org.cubco.firebase.dto.FcmTokenReq;
import org.cubco.response.CommonResponse;
import org.cubco.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fcm-token")
public class FcmController {

    private final UserService userService;

    // fcmToken 등록/갱신 API
    @PostMapping
    public CommonResponse<?> registerFcmToken(@UserId Long userId, @RequestBody @Valid FcmTokenReq request) {
        userService.updateFcmToken(userId, request.getFcmToken());
        return CommonResponse.successWithMessage(HttpStatus.OK, "FCM 토큰 등록/갱신 완료");
    }

    // 로그아웃 시 토큰 삭제 API
    @DeleteMapping
    public CommonResponse<?> deleteFcmToken(@UserId Long userId) {
        userService.deleteFcmToken(userId);
        return CommonResponse.successWithMessage(HttpStatus.OK, "FCM 토큰 삭제 완료");
    }
}
