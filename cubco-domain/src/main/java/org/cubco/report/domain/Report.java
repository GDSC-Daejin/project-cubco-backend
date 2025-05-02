package org.cubco.report.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.cubco.common.BaseTimeEntity;
import org.cubco.user.domain.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@MappedSuperclass
public abstract class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User reporter;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "reason")
    private ReportReason reason;
}
