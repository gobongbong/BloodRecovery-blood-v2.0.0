package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class GetBloodCardCountRspDto {
    Integer cardCnt;
}
