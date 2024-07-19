package com.potatoes.bloodrecovery.domain.model.commands;

import com.potatoes.bloodrecovery.domain.model.valueobjects.DirectedDonation;
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
public class ModifyBloodRequestCommand {
    private String cid;
    private String requestType;
    private Integer bloodReqCnt;
    private String title;
    private String contents;

    private DirectedDonation directInfo;

    private Long requestId;
}
