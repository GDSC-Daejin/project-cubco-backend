package org.cubco.repository;

import org.cubco.coupon.domain.Coupon;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c JOIN FETCH c.user WHERE c.user.id = :userId")
    List<Coupon> findAllByUserId(@Param("userId") Long userId);

    Optional<Coupon> findByUserIdAndId(Long userId, Long couponId);
}

