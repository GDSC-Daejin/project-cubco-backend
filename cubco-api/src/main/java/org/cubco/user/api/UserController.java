package org.cubco.user.api;

import lombok.RequiredArgsConstructor;
import org.cubco.user.dto.response.UserJwtRes;
import org.cubco.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final AuthService authService;

    @PostMapping("/test")
    public ResponseEntity<UserJwtRes> testLogin() {
        UserJwtRes userJwtRes = authService.testLogin();
        return ResponseEntity.ok(userJwtRes);
    }

    // Role이 MANAGER인 유저 생성
    @PostMapping("/testManager")
    public ResponseEntity<UserJwtRes> testManagerLogin() {
        UserJwtRes userJwtRes = authService.testManagerLogin();
        return ResponseEntity.ok(userJwtRes);
    }
}
