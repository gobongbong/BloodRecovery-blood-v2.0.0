package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DonationBloodCardReqDto {
    @NotNull
    private Long requestId;
    @NotNull
    private Integer cardCnt;
}
