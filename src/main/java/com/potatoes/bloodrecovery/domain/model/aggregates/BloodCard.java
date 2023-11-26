package com.potatoes.bloodrecovery.domain.model.aggregates;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
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
@Table(name = "blood_card")
@ToString
public class BloodCard {

    @Id
    @GeneratedValue
    private Long bloodCardId;

    private String cid;

    private String code;
    private String donationType;
    private String name;
    private String date;

    public BloodCard(RegisterBloodCardCommand registerBloodCardCommand) {
        this.cid = registerBloodCardCommand.getCid();
        this.code = registerBloodCardCommand.getCode();
        this.donationType = registerBloodCardCommand.getDonationType();
        this.name = registerBloodCardCommand.getName();
        this.date = registerBloodCardCommand.getDate();
    }

    public boolean isValidBloodCard(){
        //BIMS 외부 연동 불가
        return true;
    }
}
