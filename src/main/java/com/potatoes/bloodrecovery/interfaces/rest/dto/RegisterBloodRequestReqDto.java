package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBloodRequestReqDto {
    @NotBlank
    private String requestType;
    @NotBlank
    private Integer bloodReqCnt;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;

    private DirectedDonation directInfo;
}
