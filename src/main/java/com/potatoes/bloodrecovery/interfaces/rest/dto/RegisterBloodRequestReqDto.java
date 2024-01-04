package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBloodRequestReqDto {
    private String requestType;
    private Integer bloodReqCnt;
    private String title;
    private String contents;

    private List<DirectedDonation> directInfo;

    @Builder
    @ToString
    @NoArgsConstructor
    public static class DirectedDonation {
        private String hospitalName;
        private String patientName;
        private String roomNumber;
    }
}