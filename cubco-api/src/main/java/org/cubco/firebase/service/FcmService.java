package org.cubco.firebase.service;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FcmService {

    public void sendNotification(Message message) {

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            log.error("FCM 전송 실패: {}", e.getMessage(), e);
        }
    }
}