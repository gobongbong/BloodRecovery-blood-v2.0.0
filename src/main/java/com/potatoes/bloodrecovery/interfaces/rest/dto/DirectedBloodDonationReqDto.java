package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
<<<<<<< HEAD
=======
@NoArgsConstructor
>>>>>>> ae14955f91ceb79334624d7a58822365b98282f1
public class DirectedBloodDonationReqDto {
    @NotNull
    private Long requestId;
}