package org.cubco.coupon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponUseReq {

    @Min(value = 1,  message = "1개 이상 입력해야 합니다.")
    @Max(value = 10, message = "최대 10개까지 가능합니다.")
    @Schema(description = "차감할 count", example = "5")
    private int count;

}
