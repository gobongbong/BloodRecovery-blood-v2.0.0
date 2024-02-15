package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.RequestStatus.COMPLETE;
import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class CompleteBloodRequestCommandService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional
    public void completeBloodRequest(String cid, Long requestId){
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(requestId)
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        try {
            if (bloodRequest.getBloodDonationCnt() < bloodRequest.getBloodReqCnt()){
                userRepository.requestPoint(cid, POINT_PLUS, 50 * (bloodRequest.getBloodReqCnt() - bloodRequest.getBloodDonationCnt()));
            }
            bloodRequest.changeRequestStatus(COMPLETE);
        }catch (Exception e){
            throw new ApiException(FAIL_COMPLETE_BLOOD_REQUEST);
        }
    }
}
