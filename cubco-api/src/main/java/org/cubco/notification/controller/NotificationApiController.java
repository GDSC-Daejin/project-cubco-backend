package org.cubco.notification.controller;

import org.cubco.auth.resolver.UserId;
import org.cubco.response.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationApiController {

    private final NotificationService notificationService;

    public NotificationApiController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public CommonResponse<T> register(@RequestBody String token, @UserId String userId) {
        notificationService.register(userId, token);
        return CommonResponse.successWithMessage();
    }

}