package com.potatoes.bloodrecovery.domain.model.view;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestView {
    Long requestId;
    String userNickname;
    String profileImage;
    String requestType;
    Integer bloodReqCnt;
    Integer bloodDonationCnt;
    String title;
    LocalDateTime date;

    public BloodRequestView(BloodRequest bloodRequest, UserInfoView userInfoView) {
        this.requestId = bloodRequest.getRequestId();
        this.userNickname = userInfoView.getNickname();
        this.profileImage = userInfoView.getFileNm();
        this.requestType = bloodRequest.getRequestType();
        this.bloodReqCnt = bloodRequest.getBloodReqCnt();
        this.bloodDonationCnt = bloodRequest.getBloodDonationCnt();
        this.title = bloodRequest.getPost().getTitle();
        this.date = bloodRequest.getPost().getRegDate();
    }
}
