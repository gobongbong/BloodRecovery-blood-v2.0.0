package com.potatoes.bloodrecovery.domain.model.view;

import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class UserRequestInfoView {
    private String gradeSn;
    private String userNickname;
    private String bloodStatus;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;
    private Post post;
}
