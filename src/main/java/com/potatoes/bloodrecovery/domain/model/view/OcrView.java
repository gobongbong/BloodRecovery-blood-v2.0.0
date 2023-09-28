package com.potatoes.bloodrecovery.domain.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class OcrView {
    private String code;
    private String donationType;
    private String name;
    private String date;
}
