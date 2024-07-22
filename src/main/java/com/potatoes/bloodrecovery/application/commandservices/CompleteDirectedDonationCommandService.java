package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.aggregates.DonationHistory;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.DonationHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import com.potatoes.bloodrecovery.interfaces.rest.dto.CompleteDirectedDonationReqDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.potatoes.constants.DonationStatus.DIRECTED_DONATION_ONGOING;
import static com.potatoes.constants.RequestStatus.COMPLETE;
import static com.potatoes.constants.ResponseCode.FAIL_COMPLETE_BLOOD_REQUEST;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_DONATION;
import static com.potatoes.constants.StaticValues.DIRECTED_DONATION;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class CompleteDirectedDonationCommandService {

    private final DonationHistoryRepository donationHistoryRepository;
    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Transactional
    public void completeDirectedDonation(Long requestId, CompleteDirectedDonationReqDto completeDirectedDonationReqDto){
        try {
            for (String cid : completeDirectedDonationReqDto.getCidList()) {
                List<DonationHistory> donationHistories = donationHistoryRepository.findByCidAndDonationTypeAndDonationStatus(cid, DIRECTED_DONATION, DIRECTED_DONATION_ONGOING);
                if (ObjectUtils.isEmpty(donationHistories)){
                    throw new ApiException(NO_BLOOD_DONATION);
                }

                DonationHistory donationHistory = donationHistories.get(0);
                donationHistory.changeDonationStatus(COMPLETE.getValue());

                userRepository.requestPoint(cid, POINT_PLUS, 50);

                BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(requestId).orElseThrow();
                bloodRequest.changeRequestStatus(COMPLETE);

                //todo 알람 호출(지정헌혈 신청자에게)
            }
        } catch (Exception e){
            throw new ApiException(FAIL_COMPLETE_BLOOD_REQUEST);
        }
    }
}
