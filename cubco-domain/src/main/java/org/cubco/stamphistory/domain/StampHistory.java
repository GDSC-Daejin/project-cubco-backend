package org.cubco.stamphistory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.cafe.domain.Cafe;
import org.cubco.common.BaseTimeEntity;
import org.cubco.user.domain.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "stamp_histories")
public class StampHistory extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private StampStatus status;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt; // 승인 시간. 승인될 경우에만 ( 승인 안 되면 modifiedAt 으로 대체 가능 )

    /**
     * 스탬프 내역은 적립 요청 시 최초 생성 되기에 approvedAt은 null, 나중에 승인 될 경우 update 비즈니스 로직 추가
     */
    public static StampHistory create(User user, Cafe cafe, StampStatus status) {
        return StampHistory.builder()
                .user(user)
                .cafe(cafe)
                .status(status)
                .build();
    }

    public void approve() {
        this.status = StampStatus.ACCEPTED;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = StampStatus.REJECTED;
        this.approvedAt = LocalDateTime.now();
    }

}