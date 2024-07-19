package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.constants.RequestStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static com.potatoes.constants.StaticValues.*;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "donation_history")
@ToString
public class DonationHistory {

    @Id
    @GeneratedValue
    private Long historyId;

    private Long requestId;
    private String cid;

    private Integer donationCnt;
    private String donationType;
    private RequestStatus donationStatus;

    @CreatedDate
    private LocalDateTime date;

    public DonationHistory(DonationBloodCardCommand donationBloodCardCommand) {
        this.requestId = donationBloodCardCommand.getRequestId();
        this.cid = donationBloodCardCommand.getCid();
        this.donationCnt = donationBloodCardCommand.getCardCnt();
        this.donationType = BLOOD_CARD_DONATION;
    }

    /**
     * 지정헌혈 이력 생성자
     */
    public DonationHistory(DirectedBloodDonationCommand directedBloodDonationCommand) {
        this.requestId = directedBloodDonationCommand.getRequestId();
        this.cid = directedBloodDonationCommand.getCid();
        this.donationCnt = 1;
        this.donationType = DIRECTED_DONATION;
        this.donationStatus = RequestStatus.DIRECTED_DONATION_ONGOING;
    }

    public void changeRequestStatus(RequestStatus requestStatus) {
        this.donationStatus = requestStatus;
    }
}
