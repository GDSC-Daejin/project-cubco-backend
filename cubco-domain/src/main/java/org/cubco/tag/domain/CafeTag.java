package org.cubco.tag.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.cafe.domain.Cafe;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "cafe_tags")
public class CafeTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @NotNull
    @Column(name = "name")
    private String name;

    public static CafeTag create(Cafe cafe, String name) {
        return CafeTag.builder()
                .cafe(cafe)
                .name(name)
                .build();
    }
}
