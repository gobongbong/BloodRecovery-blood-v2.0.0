package com.potatoes.bloodrecovery.domain.model.commands;

import com.potatoes.bloodrecovery.interfaces.rest.dto.RegisterBloodRequestReqDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
@Builder
public class RegisterBloodRequestCommand {
    private String cid;
    private String requestType;
    private Integer bloodReqCnt;
    private List<RegisterBloodRequestReqDto.Content> contentInfo;
    private List<RegisterBloodRequestReqDto.DirectedDonation> directInfo;

    @Builder
    @ToString
    @NoArgsConstructor
    public static class Content {
        private String title;
        private String contents;
    }

    @Builder
    @ToString
    @NoArgsConstructor
    public static class DirectedDonation {
        private String hospitalName;
        private String patientName;
        private String roomNumber;
    }
}
