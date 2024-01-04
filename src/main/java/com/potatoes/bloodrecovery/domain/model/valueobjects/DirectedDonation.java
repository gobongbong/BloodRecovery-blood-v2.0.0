package com.potatoes.bloodrecovery.domain.model.valueobjects;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;

@Slf4j
@Getter
@Builder
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DirectedDonation {

    private String hospitalName;
    private String patientName;
    private String roomNumber;

    public DirectedDonation(RegisterBloodRequestCommand registerBloodRequestCommand) {

    }
}
