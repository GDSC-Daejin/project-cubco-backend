package org.cubco.image.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.common.BaseTimeEntity;
import org.cubco.curation.domain.Curation;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "curation_images")
public class CurationImage extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_id")
    @NotNull
    private Curation curation;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(name = "sort_order")
    private int sortOrder;

    public static CurationImage create(Curation curation, String imageUrl, int sortOrder) {
        return CurationImage.builder()
                .curation(curation)
                .imageUrl(imageUrl)
                .sortOrder(sortOrder)
                .build();
    }
}
