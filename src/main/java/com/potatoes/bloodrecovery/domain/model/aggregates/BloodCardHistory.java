package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.constants.BloodCardStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long bloodCardId;

    private String customerId;

    private String code;
    private String donationType;
    private String name;
    private String date;
    private BloodCardStatus status;

    public BloodCardHistory(RegisterBloodCardCommand registerBloodCardCommand) {
        this.bloodCardId = registerBloodCardCommand.getBloodCardId();
        this.customerId = registerBloodCardCommand.getCustomerId();
        this.code = registerBloodCardCommand.getCode();
        this.donationType = registerBloodCardCommand.getDonationType();
        this.name = registerBloodCardCommand.getName();
        this.date = registerBloodCardCommand.getDate();
        this.status = REGISTER;
    }

    public BloodCardHistory(DeleteBloodCardCommand deleteBloodCardCommand) {
        this.bloodCardId = deleteBloodCardCommand.getBloodCardId();
        this.customerId = deleteBloodCardCommand.getCustomerId();
        this.code = deleteBloodCardCommand.getCode();
        this.donationType = deleteBloodCardCommand.getDonationType();
        this.name = deleteBloodCardCommand.getName();
        this.date = deleteBloodCardCommand.getDate();
        this.status = DELETE;
    }
}
