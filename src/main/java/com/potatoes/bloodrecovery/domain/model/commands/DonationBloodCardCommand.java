package com.potatoes.bloodrecovery.domain.model.commands;

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
public class DonationBloodCardCommand {
    private String cid;
    private Long requestId;
    private Integer cardCnt;
}
