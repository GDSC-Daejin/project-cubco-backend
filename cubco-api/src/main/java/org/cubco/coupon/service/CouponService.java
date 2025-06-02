package org.cubco.coupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cubco.coupon.domain.Coupon;
import org.cubco.coupon.dto.response.CouponDetailRes;
import org.cubco.coupon.dto.response.CouponImageUpdateRes;
import org.cubco.coupon.dto.response.CouponRes;
import org.cubco.coupon.dto.response.CouponUseRes;
import org.cubco.exception.coupon.CouponNotFoundException;
import org.cubco.policy.CouponOwnershipPolicy;
import org.cubco.policy.CouponRemainingCountPolicy;
import org.cubco.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponRemainingCountPolicy countPolicy;
    private final CouponOwnershipPolicy ownershipPolicy;

    // [1] 사용자 보유 쿠폰 목록 조회
    public List<CouponRes> getCouponsByUser(Long userId) {
        List<Coupon> coupons = couponRepository.findAllByUserId(userId);

        if (coupons.isEmpty()) throw new CouponNotFoundException();

        return coupons.stream()
                .map(CouponRes::of)
                .toList();
    }

    // [2] 쿠폰 상세 조회
    public CouponDetailRes getCouponDetail(Long userId, Long couponId) {
        Coupon coupon = couponRepository.findByUserIdAndId(userId, couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        return CouponDetailRes.of(coupon);
    }

    // [3] 쿠폰 이미지 수정
    @Transactional
    public CouponImageUpdateRes updateCouponImage(Long userId, Long couponId, String imageUrl) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        // 수정 권한이 있는지 검증
        ownershipPolicy.validateCouponOwner(coupon, userId);

        coupon.updateImage(imageUrl);

        return CouponImageUpdateRes.of(coupon);
    }

    // [4] 쿠폰 사용 처리 (count 차감)
    @Transactional
    public CouponUseRes useCoupon(Long userId, Long couponId, int count) {
        Coupon coupon = couponRepository.findByUserIdAndId(userId, couponId)
                .orElseThrow(() -> new CouponNotFoundException());

        // 수정 권한이 있는지 검증
        ownershipPolicy.validateCouponOwner(coupon, userId);
        // count가 coupon의 stampCount보다 작은지 검증
        countPolicy.validateCouponCount(coupon, count);

        coupon.updateCount(count);
        return CouponUseRes.of(coupon);
    }

}