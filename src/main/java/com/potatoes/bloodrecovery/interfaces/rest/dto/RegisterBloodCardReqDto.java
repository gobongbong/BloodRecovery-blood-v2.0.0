package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBloodCardReqDto {
    private String code;
    private String donationType;
    private String name;
    private String date;
}
