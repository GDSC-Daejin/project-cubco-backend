package org.cubco.scrap.domain;

import jakarta.persistence.*;
import lombok.*;
import org.cubco.cafe.domain.Cafe;
import org.cubco.common.BaseTimeEntity;
import org.cubco.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "scraps")
public class Scrap extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Scrap create(Cafe cafe, User user) {
        return Scrap.builder()
                .cafe(cafe)
                .user(user)
                .build();
    }
}