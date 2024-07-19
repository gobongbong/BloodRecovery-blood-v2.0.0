package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.constants.RequestStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.potatoes.constants.StaticValues.BLOOD_CARD_DONATION;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "donation_history")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class DonationHistory {

    @Id
    @GeneratedValue
    private Long historyId;

    private Long requestId;
    private String cid;

    private Integer donationCnt;
    private String donationType;
    private String donationStatus;

    @CreatedDate
    private LocalDateTime date;

    public DonationHistory(DonationBloodCardCommand donationBloodCardCommand) {
        this.requestId = donationBloodCardCommand.getRequestId();
        this.cid = donationBloodCardCommand.getCid();
        this.donationCnt = donationBloodCardCommand.getCardCnt();
        this.donationType = BLOOD_CARD_DONATION;
    }

    public void changeDonationStatus(String donationStatus) {
        this.donationStatus = donationStatus;
    }
}
