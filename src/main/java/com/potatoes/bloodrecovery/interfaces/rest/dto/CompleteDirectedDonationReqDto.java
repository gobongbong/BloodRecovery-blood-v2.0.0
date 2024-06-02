package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompleteDirectedDonationReqDto {
    private List<String> cidList;
}
