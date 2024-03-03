package com.potatoes.bloodrecovery.interfaces.rest.dto;

import com.potatoes.bloodrecovery.domain.model.view.DonationHistoryView;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetDonationHistoryRspDto {
    List<DonationHistoryView> donationHistory;
}
