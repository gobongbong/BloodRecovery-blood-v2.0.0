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

import java.util.List;

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

        if(!bloodRequest.getRequestType().equals(DIRECTED_DONATION)) {
            throw new ApiException(BAD_REQUEST_TYPE);
        }

        List<DonationHistory> donationHistories = donationHistoryRepository.findByCidAndDonationTypeAndDonationStatus(
                directedBloodDonationCommand.getCid(), DIRECTED_DONATION, DIRECTED_DONATION_ONGOING);

        if(!donationHistories.isEmpty()) {
            throw new ApiException(EXIST_ONGOING_DIRECTED_BLOOD_DONATION_HISTORY);
        }

        try {
            changeRequestStatusAndDonationCnt(bloodRequest);

            DonationHistory donationHistory = new DonationHistory(directedBloodDonationCommand);
            donationHistoryRepository.save(donationHistory);
        } catch (Exception e) {
            log.error("지정헌혈 신청 처리 중 DB ERROR 발생 + {}", e);
            throw  e;
        }
    }

    private void changeRequestStatusAndDonationCnt(BloodRequest bloodRequest) {
        if (bloodRequest.getRequestStatus().equals(REGISTER)) {
            bloodRequest.changeRequestStatus(DIRECTED_DONATION_ONGOING);
        }

        if (bloodRequest.getRequestStatus().equals(DIRECTED_DONATION_ONGOING)) {
            if (bloodRequest.getBloodDonationCnt() + 1 == bloodRequest.getBloodReqCnt()) {
                bloodRequest.changeRequestStatus(COMPLETE);
            }
        }

        bloodRequest.changeBloodDonationCnt(1);
    }
}