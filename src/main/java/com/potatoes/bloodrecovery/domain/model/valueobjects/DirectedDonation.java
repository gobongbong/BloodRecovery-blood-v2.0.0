package com.potatoes.bloodrecovery.domain.model.valueobjects;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.util.List;

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
        List<DirectedDonation> donationList = registerBloodRequestCommand.getDirectInfo();
        for (DirectedDonation directedDonation : donationList) {
            this.hospitalName = directedDonation.getHospitalName();
            this.patientName = directedDonation.getPatientName();
            this.roomNumber = directedDonation.getRoomNumber();
        }
    }

    public DirectedDonation(ModifyBloodRequestCommand modifyBloodRequestCommand) {
        List<DirectedDonation> donationList = modifyBloodRequestCommand.getDirectInfo();
        for (DirectedDonation directedDonation : donationList) {
            this.hospitalName = directedDonation.getHospitalName();
            this.patientName = directedDonation.getPatientName();
            this.roomNumber = directedDonation.getRoomNumber();
        }
    }
}
