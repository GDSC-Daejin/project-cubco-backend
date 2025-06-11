package org.cubco.user.service;

import lombok.RequiredArgsConstructor;
import org.cubco.auth.jwt.JWTUtil;
import org.cubco.user.domain.RoleType;
import org.cubco.user.domain.SocialType;
import org.cubco.user.domain.Status;
import org.cubco.user.domain.User;
import org.cubco.user.dto.response.UserJwtRes;
import org.cubco.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JWTUtil jwtutil;
    private final UserRepository userRepository;

    public UserJwtRes testLogin() {
        User user = User.create("test", "test@test.com", "010-1234-5678", "서울 성북구", SocialType.TEST, "1", Status.ACTIVE, RoleType.MEMBER);
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        }
        String token = jwtutil.createToken(user.getId(), user.getRoleType().name());
        return UserJwtRes.of(user.getId(), token);
    }

    public UserJwtRes testManagerLogin() {
        User user = User.create("test2", "test2@test.com", "010-1234-1234", "서울 강남구", SocialType.TEST, "1", Status.ACTIVE, RoleType.MANAGER);
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        }
        String token = jwtutil.createToken(user.getId(), user.getRoleType().name());
        return UserJwtRes.of(user.getId(), token);
    }
}
