package org.cubco.firebase.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FcmTokenReq {
    @NotBlank
    private String fcmToken;
}
