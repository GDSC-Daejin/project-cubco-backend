package org.cubco.qrcode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cubco.coupon.domain.Coupon;
import org.cubco.coupon.dto.response.CouponImageUpdateRes;

@Getter
@AllArgsConstructor
public class GuestQrRes {
    private String qrImage;
    private String qrKey;
    private String qrUrl;

    public static GuestQrRes of(String qrImage, String qrKey, String qrUrl) {
        return new GuestQrRes(
                qrImage,
                qrKey,
                qrUrl
        );
    }
}