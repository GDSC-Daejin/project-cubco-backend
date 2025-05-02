package org.cubco.report.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.cubco.curation.domain.Curation;
import org.cubco.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Table(name = "curation_reports")
public class CurationReport extends Report {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_id")
    private Curation curation;

    public static CurationReport create(User reporter, Curation curation, ReportReason reason) {
        return CurationReport.builder()
                .reporter(reporter)
                .curation(curation)
                .reason(reason)
                .build();
    }
}