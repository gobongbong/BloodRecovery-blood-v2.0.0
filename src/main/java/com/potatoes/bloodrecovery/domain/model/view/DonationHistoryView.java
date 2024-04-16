package com.potatoes.bloodrecovery.domain.model.view;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DonationHistoryView {
    private Long historyId;
    private String donationType;
    private Integer donationCnt;
    private LocalDateTime donationDate;
}
