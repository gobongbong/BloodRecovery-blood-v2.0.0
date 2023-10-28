package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.view.OcrView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class GetBloodCardsRspDto {
    List<BloodCard> cards;
}
