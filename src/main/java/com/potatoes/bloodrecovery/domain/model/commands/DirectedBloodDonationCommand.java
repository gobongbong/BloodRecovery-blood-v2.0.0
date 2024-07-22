package com.potatoes.bloodrecovery.domain.model.commands;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class DirectedBloodDonationCommand {
    private String cid;
    private Long requestId;
}