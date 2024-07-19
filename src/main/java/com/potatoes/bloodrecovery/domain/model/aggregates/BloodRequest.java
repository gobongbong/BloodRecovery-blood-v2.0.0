package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
import com.potatoes.bloodrecovery.domain.model.valueobjects.Post;
import com.potatoes.constants.RequestStatus;
import com.potatoes.converter.RequestStatusConverter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Objects;

import static com.potatoes.constants.RequestStatus.*;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "blood_request")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BloodRequest {

    @Id
    @GeneratedValue
    private Long requestId;
    private String cid;
    @Convert(converter = RequestStatusConverter.class)
    private RequestStatus requestStatus;
    private String requestType;
    private Integer bloodReqCnt;
    private Integer bloodDonationCnt;

    @Embedded
    private Post post;
    @Embedded
    private DirectedDonation directedDonation;

    @Transient
    private boolean editable;

    public BloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand) {
        this.cid = registerBloodRequestCommand.getCid();
        this.requestType = registerBloodRequestCommand.getRequestType();
        this.bloodReqCnt = registerBloodRequestCommand.getBloodReqCnt();
        this.bloodDonationCnt = 0;
        this.requestStatus = REGISTER;
        this.post = new Post(registerBloodRequestCommand);
        if (Objects.nonNull(registerBloodRequestCommand.getDirectInfo())){
            this.directedDonation = new DirectedDonation(registerBloodRequestCommand);
        }
    }

    public void modifyBloodRequest(ModifyBloodRequestCommand modifyBloodRequestCommand) {
        this.cid = modifyBloodRequestCommand.getCid();
        this.requestType = modifyBloodRequestCommand.getRequestType();
        this.bloodReqCnt = modifyBloodRequestCommand.getBloodReqCnt();
        this.post = new Post(modifyBloodRequestCommand);
        if (Objects.nonNull(modifyBloodRequestCommand.getDirectInfo())){
            new DirectedDonation(modifyBloodRequestCommand);
        }
    }

    public boolean deletableBloodRequest(){
        return this.bloodDonationCnt == 0;
    }
    public void deleteBloodRequest(){
        this.requestStatus = DELETE;
    }

    public boolean isModifiable(){
        return !this.requestStatus.equals(DIRECTED_DONATION_ONGOING) && !this.requestStatus.equals(ONGOING);
    }

    public void changeRequestStatus(RequestStatus requestStatus){
        this.requestStatus = requestStatus;
    }

    public void changeBloodDonationCnt(Integer donatedBloodCardCnt){
        this.bloodDonationCnt = bloodDonationCnt + donatedBloodCardCnt;
    }
}