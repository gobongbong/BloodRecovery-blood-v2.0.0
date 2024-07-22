package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DirectedBloodDonationReqDto {
    @NotNull
    private Long requestId;
}