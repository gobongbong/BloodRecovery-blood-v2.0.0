package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "blood_request")
@ToString
public class BloodRequest {

    @Id
    @GeneratedValue
    private Long requestId;

    private String cid;
    private String userNickname;

    private String requestType;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;

    private Post post;

    private DirectedDonation directedDonation;

    private boolean editable;

    public BloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand, String nickName) {
        this.cid = registerBloodRequestCommand.getCid();
        this.userNickname = nickName;
        this.requestType = registerBloodRequestCommand.getRequestType();
        this.bloodReqCnt = registerBloodRequestCommand.getBloodReqCnt();
        this.post = new Post(registerBloodRequestCommand);
        if (!registerBloodRequestCommand.getDirectInfo().isEmpty()){
            //todo 존재하면 값 셋팅
        }
    }
}
