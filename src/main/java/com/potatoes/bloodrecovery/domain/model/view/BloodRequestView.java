package com.potatoes.bloodrecovery.domain.model.view;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestView {
    private String userNickName;
    private String profileImage;
    private String requestType;
    private boolean editable;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;
    //todo list?
    private Post postInfo;
    //todo list?
    private DirectedDonation directInfo;

    public BloodRequestView(BloodRequest bloodRequest, UserInfoView userInfoView, boolean editable) {
        this.userNickName = userInfoView.getNickname();
        this.profileImage = userInfoView.getFileNm();
        this.requestType = bloodRequest.getRequestType();
        this.editable = editable;
        this.bloodReqCnt = bloodRequest.getBloodReqCnt();
        this.bloodDonationCnt = bloodRequest.getBloodDonationCnt();
        this.postInfo = bloodRequest.getPost();
        this.directInfo = bloodRequest.getDirectedDonation();
    }
}