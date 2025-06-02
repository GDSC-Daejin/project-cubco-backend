package org.cubco.stamphistory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestStampReq {
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 숫자만 포함하며 10~11자리여야 합니다.")
    private String phone;
    private Long cafeId;
}