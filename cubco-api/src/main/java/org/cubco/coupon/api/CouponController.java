package org.cubco.coupon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.auth.resolver.UserId;
import org.cubco.coupon.dto.request.CouponImageUpdateReq;
import org.cubco.coupon.dto.request.CouponUseReq;
import org.cubco.coupon.dto.response.CouponDetailRes;
import org.cubco.coupon.dto.response.CouponImageUpdateRes;
import org.cubco.coupon.dto.response.CouponRes;
import org.cubco.coupon.dto.response.CouponUseRes;
import org.cubco.response.CommonResponse;
import org.cubco.coupon.service.CouponService;
import org.cubco.swagger.CouponApiDocs;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController implements CouponApiDocs {

    private final CouponService couponService;

    // 1. 사용자 보유 쿠폰 리스트 조회
    @GetMapping("/list")
    public CommonResponse<List<CouponRes>> getUserCoupons(@UserId Long userId) {
        List<CouponRes> coupons = couponService.getCouponsByUser(userId);
        return CommonResponse.success(HttpStatus.OK, "보유 쿠폰 목록 조회 성공", coupons);
    }

    // 2. 쿠폰 상세 조회
    @GetMapping("/{couponId}")
    public CommonResponse<CouponDetailRes> getCouponDetail(
            @UserId Long userId,
            @PathVariable Long couponId
    ) {
        CouponDetailRes coupon = couponService.getCouponDetail(userId, couponId);
        return CommonResponse.success(HttpStatus.OK, "쿠폰 상세 조회 성공", coupon);
    }

    // 3. 쿠폰 이미지 변경
    @PatchMapping("/{couponId}/image")
    public CommonResponse<?> updateCouponImage(
            @UserId Long userId,
            @PathVariable Long couponId,
            @RequestBody @Valid CouponImageUpdateReq request
    ) {
        CouponImageUpdateRes coupon = couponService.updateCouponImage(userId, couponId, request.getImageUrl());
        return CommonResponse.success(HttpStatus.OK, "쿠폰 이미지가 변경되었습니다.", coupon);
    }

    // 4. 쿠폰 사용 (count 차감)
    @PatchMapping("/{couponId}/use")
    public CommonResponse<?> useCoupon(
            @UserId Long userId,
            @PathVariable Long couponId,
            @RequestBody @Valid CouponUseReq request
    ) {
        CouponUseRes coupon = couponService.useCoupon(userId, couponId, request.getCount());
        return CommonResponse.success(HttpStatus.OK, "쿠폰이 사용되었습니다.", coupon);
    }

}
