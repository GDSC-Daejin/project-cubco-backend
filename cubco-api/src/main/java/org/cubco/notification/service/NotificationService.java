package org.cubco.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.firebase.service.FcmService;
import org.cubco.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FcmService fcmService;

    public void notifyStampApproval(User user) {
        if (user.getFcmToken() == null) {
            log.warn("유저 {}: fcmToken 없음, 알림 발송 스킵", user.getId());
            return;
        }

        // 메시지 설정
        String title = "적립 완료!";
        String body = "큐레이션을 작성하시겠습니까?";

        Message message = Message.builder()
                .setToken(user.getFcmToken())
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        // FCM으로 메시지 전송
        fcmService.sendNotification(message);

    }
}