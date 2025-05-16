package org.cubco.dto.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;

@Getter
@AllArgsConstructor
public class CouponUseRequest {

    @Min(1)
    @Max(10)
    @Schema(description = "차감할 count", example = "5")
    private int count;

    public static CouponUseRequest of(Coupon coupon) {
        return new CouponUseRequest(
                coupon.getCount()
        );
    }

}
