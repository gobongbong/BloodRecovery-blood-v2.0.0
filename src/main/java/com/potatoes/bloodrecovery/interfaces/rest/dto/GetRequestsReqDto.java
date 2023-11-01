package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestsReqDto {
    private String cid;
}
