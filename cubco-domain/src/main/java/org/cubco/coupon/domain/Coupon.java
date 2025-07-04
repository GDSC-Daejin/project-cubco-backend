package org.cubco.coupon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.cafe.domain.Cafe;
import org.cubco.common.BaseTimeEntity;
import org.cubco.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "coupons")
public class Coupon extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Builder.Default
    @NotNull
    @Column(name = "count")
    private int count = 0;

    @Column(name = "image_url")
    private String imageUrl;

    public static Coupon create(User user, Cafe cafe, int count, String imageUrl) {
        return Coupon.builder()
                .user(user)
                .cafe(cafe)
                .count(count)
                .imageUrl(imageUrl)
                .build();
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateCount(int count) {
        this.count = count;
    }
}
