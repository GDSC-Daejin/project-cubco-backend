package org.cubco.coupon.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.auth.resolver.UserId;
import org.cubco.coupon.dto.request.CouponImageUpdateRequest;
import org.cubco.coupon.dto.request.CouponUseRequest;
import org.cubco.coupon.dto.response.CouponDetailResponse;
import org.cubco.coupon.dto.response.CouponImageUpdateResponse;
import org.cubco.coupon.dto.response.CouponResponse;
import org.cubco.coupon.dto.response.CouponUseResponse;
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
    public CommonResponse<List<CouponResponse>> getUserCoupons(@UserId Long userId) {
        List<CouponResponse> coupons = couponService.getCouponsByUser(userId);
        return CommonResponse.createSuccess(HttpStatus.OK, "보유 쿠폰 목록 조회 성공", coupons);
    }

    // 2. 쿠폰 상세 조회
    @GetMapping("/{couponId}")
    public CommonResponse<CouponDetailResponse> getCouponDetail(
            @UserId Long userId,
            @PathVariable Long couponId
    ) {
        CouponDetailResponse coupon = couponService.getCouponDetail(userId, couponId);
        return CommonResponse.createSuccess(HttpStatus.OK, "쿠폰 상세 조회 성공", coupon);
    }

    // 3. 쿠폰 이미지 변경
    @PatchMapping("/{couponId}/image")
    public CommonResponse<?> updateCouponImage(
            @UserId Long userId,
            @PathVariable Long couponId,
            @RequestBody @Valid CouponImageUpdateRequest request
    ) {
        CouponImageUpdateResponse coupon = couponService.updateCouponImage(userId, couponId, request.getImageUrl());
        return CommonResponse.createSuccess(HttpStatus.OK, "쿠폰 이미지가 변경되었습니다.", coupon);
    }

    // 4. 쿠폰 사용 (count 차감)
    @PatchMapping("/{couponId}/use")
    public CommonResponse<?> useCoupon(
            @UserId Long userId,
            @PathVariable Long couponId,
            @RequestBody @Valid CouponUseRequest request
    ) {
        CouponUseResponse coupon = couponService.useCoupon(userId, couponId, request.getCount());
        return CommonResponse.createSuccess(HttpStatus.OK, "쿠폰이 사용되었습니다.", coupon);
    }

}
