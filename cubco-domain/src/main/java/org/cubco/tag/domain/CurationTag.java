package org.cubco.tag.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.curation.domain.Curation;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "curation_tags")
public class CurationTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curation_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_id")
    private Curation curation;

    @NotNull
    @Column(name = "name")
    private String name;

    public static CurationTag create(Curation curation, String name) {
        return CurationTag.builder()
                .curation(curation)
                .name(name)
                .build();
    }
}
