package org.cubco.user.service;

import lombok.RequiredArgsConstructor;
import org.cubco.exception.user.UserNotFoundException;
import org.cubco.user.domain.User;
import org.cubco.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 로그인 시 fcmToken 등록 또는 갱신
    @Transactional
    public void updateFcmToken(Long userId, String fcmToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        user.updateFcmToken(fcmToken);
    }

    // 로그아웃 시 토큰 삭제
    @Transactional
    public void deleteFcmToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        user.updateFcmToken(null);
    }
}
