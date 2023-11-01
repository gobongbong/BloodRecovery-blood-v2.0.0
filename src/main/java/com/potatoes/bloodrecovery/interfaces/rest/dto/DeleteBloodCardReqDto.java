package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBloodCardReqDto {
    private String cid;
    private String bloodCardId;
}
