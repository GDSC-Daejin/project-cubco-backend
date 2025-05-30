package org.cubco.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cubco.common.BaseTimeEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Builder.Default
    @NotNull
    @Column(name = "report_count")
    private int reportCount = 0;

    @Column(name = "social_id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role_type")
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private Status status;

    public static User create(String name, String email, String phone, String city, SocialType socialType, String socialId, Status status, RoleType role) {
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .city(city)
                .socialType(socialType)
                .socialId(socialId)
                .status(status)
                .roleType(role)
                .build();
    }
}