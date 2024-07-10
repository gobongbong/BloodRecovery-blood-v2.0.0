package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompleteDirectedDonationReqDto {
    @NotBlank
    private List<String> cidList;
}
