package org.cubco.stamphistory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberStampReq {
    @NotBlank
    private String phone;

    @NotNull
    private Long cafeId;
}