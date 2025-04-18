package org.cubco.cafecuration.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.cafe.domain.Cafe;
import org.cubco.common.BaseTimeEntity;
import org.cubco.curation.domain.Curation;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "cafe_curations")
public class CafeCuration extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_curation_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_id")
    private Curation curation;

    /**
     * Ex) 강남 뷰 맛집 카페 추천 TOP5 와 같이 큐레이션 내에 여러 카페가 들어갈 경우 호출
     */
    public static CafeCuration create(Cafe cafe, Curation curation) {
        return CafeCuration.builder()
                .cafe(cafe)
                .curation(curation)
                .build();
    }
}