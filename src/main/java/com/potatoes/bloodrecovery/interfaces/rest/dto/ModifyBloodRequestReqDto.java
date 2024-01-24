package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModifyBloodRequestReqDto {
    private String requestType;
    private Integer bloodReqCnt;
    private String title;
    private String contents;

    private String postStatus;

    private DirectedDonation directInfo;
}
