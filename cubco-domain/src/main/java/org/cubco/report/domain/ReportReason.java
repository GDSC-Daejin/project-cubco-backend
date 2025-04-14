package org.cubco.report.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReportReason {

    FISHING_HARASSMENT_SPAM("스팸"),
    LEAKING_FRAUD("정보유출"),
    PORNOGRAPHY("성적인 내용"),
    INAPPROPRIATE_CONTENT("부적절한 컨텐츠"),
    INSULT("모욕"),
    COMMERCIAL_AD("광고"),
    POLITICAL_CONTENT("정치적 발언");

    private final String value;
}