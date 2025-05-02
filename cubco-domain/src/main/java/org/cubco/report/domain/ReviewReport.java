package org.cubco.report.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.cubco.review.domain.Review;
import org.cubco.user.domain.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Table(name = "review_reports")
public class ReviewReport extends Report {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public static ReviewReport create(User reporter, Review review, ReportReason reason) {
        return ReviewReport.builder()
                .reporter(reporter)
                .review(review)
                .reason(reason)
                .build();
    }
}
