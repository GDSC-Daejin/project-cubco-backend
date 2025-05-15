package org.cubco.service;

import lombok.RequiredArgsConstructor;
import org.cubco.coupon.domain.Coupon;
import org.cubco.dto.coupon.CouponDetailResponse;
import org.cubco.dto.coupon.CouponResponse;
import org.cubco.exception.CouponNotFoundException;
import org.cubco.exception.CustomException;
import org.cubco.exception.ErrorCode;
import org.cubco.policy.CouponOwnershipPolicy;
import org.cubco.policy.CouponRemainingCountPolicy;
import org.cubco.policy.CouponUsePolicy;
import org.cubco.repository.CouponRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponRemainingCountPolicy countPolicy;
    private final CouponOwnershipPolicy ownershipPolicy;

    // [1] 사용자 보유 쿠폰 목록 조회
    public List<CouponResponse> getCouponsByUser(Long userId) {
        List<Coupon> coupons = couponRepository.findAllByUserId(userId);

        if (coupons.isEmpty()) throw new CouponNotFoundException();

        return coupons.stream()
                .map(CouponResponse::of)
                .toList();
    }

    // [2] 쿠폰 상세 조회
    public CouponDetailResponse getCouponDetail(Long userId, Long couponId) {
        Coupon coupon = couponRepository.findByUserIdAndId(userId, couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        return CouponDetailResponse.of(coupon);
    }

    // [3] 쿠폰 이미지 수정
    @Transactional
    public void updateCouponImage(Long userId, Long couponId, String imageUrl) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        // 수정 권한이 있는지 검증
        ownershipPolicy.validateCouponOwner(coupon, userId);

        coupon.updateImage(imageUrl);
    }

    // [4] 쿠폰 사용 처리 (count 차감)
    @Transactional
    public void useCoupon(Long userId, Long couponId, Integer count) {
        Coupon coupon = couponRepository.findByUserIdAndId(userId, couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        // 수정 권한이 있는지 검증 + count수가 적절한지 검증
        ownershipPolicy.validateCouponOwner(coupon, userId);
        countPolicy.validateCouponCount(coupon, count);

        coupon.updateCount(count);
    }

}