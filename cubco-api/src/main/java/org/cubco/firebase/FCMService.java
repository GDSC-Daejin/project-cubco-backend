package org.cubco.firebase;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Slf4j
@Service
public class FCMService {

    public void sendNotificationAsync(String fcmToken, String title, String body) {
        Message message = Message.builder()
                .setToken(fcmToken)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("✅ FCM 전송 성공: {}", response);
        } catch (Exception e) {
            log.error("❌ FCM 전송 실패: {}", e.getMessage(), e);
        }
    }
}