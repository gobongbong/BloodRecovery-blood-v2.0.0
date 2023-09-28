package com.potatoes.bloodrecovery.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public class RegisterBloodCardCommand {
    private String code;
    private String donationType;
    private String name;
    private String date;
    private String customerId;
}
