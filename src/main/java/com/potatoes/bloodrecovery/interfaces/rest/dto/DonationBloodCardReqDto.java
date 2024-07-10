package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DonationBloodCardReqDto {
    @NotBlank
    private Long requestId;
    @NotBlank
    private Integer cardCnt;
}
