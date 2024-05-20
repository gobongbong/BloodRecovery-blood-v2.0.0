package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.model.commands.DirectedBloodDonationCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.potatoes.constants.RequestStatus.*;
import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.DIRECTED_BLOOD_DONATION;
/**
 * 지정헌혈을 처리하는 Service
 */
@Service
@RequiredArgsConstructor
public class DirectedBloodDonationCommandService {
    private final BloodRequestRepository bloodRequestRepository;
    private final DonationHistoryRepository donationHistoryRepository;

    @Transactional
    public void applyDirectedBloodDonation(DirectedBloodDonationCommand directedBloodDonationCommand) {
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestIdAndCid(directedBloodDonationCommand.getRequestId(), directedBloodDonationCommand.getCid())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        if(!bloodRequest.getRequestType().equals(DIRECTED_BLOOD_DONATION)) {
            throw new ApiException(BAD_REQUEST_TYPE);
        }

        if(bloodRequest.getRequestStatus().equals(COMPLETE)) {
            throw new ApiException(FAIL_APPLY_DIRECTED_BLOOD_DONATION);
        }

        try {
            changeRequestStatusAndDonationCnt(bloodRequest);

            // todo 수정 가능성 있음
            DonationHistory donationHistory = new DonationHistory(directedBloodDonationCommand);
            donationHistoryRepository.save(donationHistory);
        } catch (Exception e) {
            throw new ApiException(FAIL_APPLY_DIRECTED_BLOOD_DONATION);
        }
    }

    private void changeRequestStatusAndDonationCnt(BloodRequest bloodRequest) {
        if (bloodRequest.getRequestStatus().equals(REGISTER)) {
            bloodRequest.changeRequestStatus(DIRECTED_DONATION_ONGOING);
        }

        // 만약 신청이 다 찼으면 지정헌혈을 신청을 못하게 하되, 만약 마감일까지 헌혈이 되지 않으면 패널티
        if (bloodRequest.getRequestStatus().equals(DIRECTED_DONATION_ONGOING)) {
            if (bloodRequest.getBloodDonationCnt() + 1 == bloodRequest.getBloodReqCnt()) {
                bloodRequest.changeRequestStatus(COMPLETE);
            }
        }

        bloodRequest.changeBloodDonationCnt(1);
    }
}