package com.potatoes.bloodrecovery.domain.model.commands;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
@Builder
public class DeleteBloodCardCommand {
    private String cid;
    private Long bloodCardId;

    private String code;
    private String donationType;
    private String name;
    private String date;

    public void setCardInfo(BloodCard bloodCard){
        this.code = bloodCard.getCode();
        this.donationType = bloodCard.getDonationType();
        this.name = bloodCard.getName();
        this.date = bloodCard.getDate();
    }
}
