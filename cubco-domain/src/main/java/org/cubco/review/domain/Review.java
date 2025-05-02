package org.cubco.review.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.cafe.domain.Cafe;
import org.cubco.common.BaseTimeEntity;
import org.cubco.user.domain.User;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "reviews")
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Builder.Default
    @NotNull
    @Column(name = "report_count")
    private int reportCount = 0;

    public static Review create(User user, Cafe cafe, String content, BigDecimal rating) {
        return Review.builder()
                .user(user)
                .cafe(cafe)
                .content(content)
                .rating(rating)
                .build();
    }
}
