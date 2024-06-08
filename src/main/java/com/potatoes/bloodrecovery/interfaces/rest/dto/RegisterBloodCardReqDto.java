package com.potatoes.bloodrecovery.interfaces.rest.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBloodCardReqDto {

    @NotBlank
    private String code;
    @NotBlank
    private String donationType;
    @NotBlank
    private String name;
    @NotBlank
    private String date;
}
