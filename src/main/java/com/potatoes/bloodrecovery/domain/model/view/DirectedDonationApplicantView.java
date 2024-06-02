package com.potatoes.bloodrecovery.domain.model.view;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DirectedDonationApplicantView {
    private String cid;
    private String name;
    private String phone;
    private String donationStatus;
}
