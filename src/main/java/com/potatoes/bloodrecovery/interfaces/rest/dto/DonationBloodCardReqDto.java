package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DonationBloodCardReqDto {
    private Long requestId;
    private Integer cardCnt;
}
