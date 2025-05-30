package org.cubco.stamphistory.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestStampReq {
    private String guestPhone;
    private Long cafeId;
}