package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.view.OcrView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class BloodCardOcrRspDto {
    OcrView ocrView;
}
