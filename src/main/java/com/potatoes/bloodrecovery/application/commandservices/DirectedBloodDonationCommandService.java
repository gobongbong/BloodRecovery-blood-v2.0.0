package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import com.potatoes.constants.RequestStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.DonationStatus.DIRECTED_DONATION_ONGOING;
import static com.potatoes.constants.RequestStatus.*;
import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectedBloodDonationCommandService {
    private final BloodRequestRepository bloodRequestRepository;
    private final DonationHistoryRepository donationHistoryRepository;

    @Transactional
    public void applyDirectedBloodDonation(DirectedBloodDonationCommand directedBloodDonationCommand) {
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestIdAndRequestStatusIn(directedBloodDonationCommand.getRequestId(), RequestStatus.getOngoing())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        boolean donationHistories = donationHistoryRepository.existsByCidAndDonationTypeAndDonationStatus(
                directedBloodDonationCommand.getCid(), DIRECTED_DONATION, DIRECTED_DONATION_ONGOING);

        if(donationHistories) {
            throw new ApiException(EXIST_ONGOING_DIRECTED_BLOOD_DONATION_HISTORY);
        }

        try {
            changeRequestStatusAndDonationCnt(bloodRequest);

            DonationHistory donationHistory = new DonationHistory(directedBloodDonationCommand);
            donationHistoryRepository.save(donationHistory);
        } catch (Exception e) {
            throw new ApiException(FAIL_APPLY_DIRECTED_BLOOD_DONATION);
        }
    }

    private void changeRequestStatusAndDonationCnt(BloodRequest bloodRequest) {
        if (bloodRequest.getRequestStatus().equals(REGISTER)) {
            bloodRequest.changeRequestStatus(ONGOING);
        }

        if (bloodRequest.getRequestStatus().equals(ONGOING)) {
            if (bloodRequest.getBloodDonationCnt() + 1 == bloodRequest.getBloodReqCnt()) {
                bloodRequest.changeRequestStatus(COMPLETE);
            }
        }

        bloodRequest.changeBloodDonationCnt(1);
    }
}