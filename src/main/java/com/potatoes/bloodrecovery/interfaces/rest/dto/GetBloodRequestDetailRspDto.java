package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBloodRequestDetailRspDto {
    private String userNickname;
    private String profileImage;
    private String requestType;
    private boolean editable;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;
    private Post postInfo;
    private DirectedDonation directInfo;
}
