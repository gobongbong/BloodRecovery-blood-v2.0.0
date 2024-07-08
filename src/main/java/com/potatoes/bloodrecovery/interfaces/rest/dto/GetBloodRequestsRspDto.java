package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.view.BloodRequestView;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBloodRequestsRspDto {
    private List<BloodRequestView> requestInfo;
}

