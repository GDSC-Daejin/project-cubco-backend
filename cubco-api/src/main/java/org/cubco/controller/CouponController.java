package org.cubco.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.dto.coupon.CouponDetailResponse;
import org.cubco.dto.coupon.CouponImageUpdateRequest;
import org.cubco.dto.coupon.CouponResponse;
import org.cubco.dto.coupon.CouponUseRequest;
import org.cubco.response.CommonResponse;
import org.cubco.service.CouponService;
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
//    public CommonResponse<List<CouponListResponse>> getUserCoupons(@UserId Long userId) { // PR 후 주석해제
    public CommonResponse<List<CouponResponse>> getUserCoupons(Long userId) {
        List<CouponResponse> coupons = couponService.getCouponsByUser(userId);
        return CommonResponse.createSuccess(HttpStatus.OK, "보유 쿠폰 목록 조회 성공", coupons);
    }

    // 2. 쿠폰 상세 조회
    @GetMapping("/{couponId}")
    public CommonResponse<CouponDetailResponse> getCouponDetail(
//            @UserId Long userId, // PR 후 주석해제
            Long userId,
            @PathVariable Long couponId
    ) {
        CouponDetailResponse coupon = couponService.getCouponDetail(userId, couponId);
        return CommonResponse.createSuccess(HttpStatus.OK, "쿠폰 상세 조회 성공", coupon);
    }

    // 3. 쿠폰 이미지 변경
    @PatchMapping("/{couponId}/image")
    public CommonResponse<?> updateCouponImage(
//            @UserId Long userId, // PR 후 주석 해제
            Long userId,
            @PathVariable Long couponId,
            @RequestBody CouponImageUpdateRequest request
    ) {
        CouponDetailResponse coupon = couponService.updateCouponImage(userId, couponId, request.getImageUrl());
        return CommonResponse.createSuccess(HttpStatus.OK, "쿠폰 이미지가 변경되었습니다.", coupon);
    }

    // 4. 쿠폰 사용 (count 차감)
    @PatchMapping("/{couponId}/use")
    public CommonResponse<?> useCoupon(
//            @UserId Long userId, // PR 후 주석 해제
            Long userId,
            @PathVariable Long couponId,
            @RequestBody CouponUseRequest request
    ) {
        couponService.useCoupon(userId, couponId, request.getCount());
        return CommonResponse.createSuccessWithNoContent(HttpStatus.OK, "쿠폰이 사용되었습니다.");
    }

}
