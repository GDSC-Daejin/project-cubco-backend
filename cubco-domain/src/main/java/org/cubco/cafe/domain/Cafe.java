package org.cubco.cafe.domain;

import jakarta.persistence.*;
import lombok.*;
import org.cubco.common.BaseTimeEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "cafes")
public class Cafe extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    @Column(name= "manager_id")
    private Long managerId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "hours")
    private String hours;

    @Column(name = "thumbnail_url")
    private String thumbnail;

    public static Cafe create(String name, String address, String hours, String thumbnail) {
        return Cafe.builder()
                .name(name)
                .address(address)
                .hours(hours)
                .thumbnail(thumbnail)
                .build();
    }
}