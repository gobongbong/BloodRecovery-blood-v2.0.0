package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModifyBloodRequestReqDto {
    @NotBlank
    private String requestType;
    @NotNull
    private Integer bloodReqCnt;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;

    @NotBlank
    private String postStatus;

    private DirectedDonation directInfo;
}
