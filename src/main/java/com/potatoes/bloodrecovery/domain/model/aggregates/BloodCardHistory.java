package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.DonationBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.constants.BloodCardStatus;
import com.potatoes.converter.BloodCardStatusConverter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.time.LocalDate;

import static com.potatoes.constants.BloodCardStatus.*;

@Slf4j
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "blood_card_history")
@ToString
public class BloodCardHistory {

    @Id
    @GeneratedValue
    private Long historyId;

    private Long bloodCardId;
    private String cid;

    private String code;
    private String donationType;
    private String name;
    private LocalDate date;
    @Convert(converter = BloodCardStatusConverter.class)
    private BloodCardStatus status;

    public BloodCardHistory(RegisterBloodCardCommand registerBloodCardCommand) {
        this.bloodCardId = registerBloodCardCommand.getBloodCardId();
        this.cid = registerBloodCardCommand.getCid();
        this.code = registerBloodCardCommand.getCode();
        this.donationType = registerBloodCardCommand.getDonationType();
        this.name = registerBloodCardCommand.getName();
        this.date = registerBloodCardCommand.getDate();
        this.status = REGISTER;
    }

    public BloodCardHistory(DeleteBloodCardCommand deleteBloodCardCommand) {
        this.bloodCardId = deleteBloodCardCommand.getBloodCardId();
        this.cid = deleteBloodCardCommand.getCid();
        this.code = deleteBloodCardCommand.getCode();
        this.donationType = deleteBloodCardCommand.getDonationType();
        this.name = deleteBloodCardCommand.getName();
        this.date = deleteBloodCardCommand.getDate();
        this.status = DELETE;
    }

    public BloodCardHistory(DonationBloodCardCommand donationBloodCardCommand, BloodCard bloodCard) {
        this.bloodCardId = bloodCard.getBloodCardId();
        this.cid = donationBloodCardCommand.getCid();
        this.code = bloodCard.getCode();
        this.donationType = bloodCard.getDonationType();
        this.name = bloodCard.getName();
        this.date = bloodCard.getDate();
        this.status = OWNER_CHANGE;
    }
}
