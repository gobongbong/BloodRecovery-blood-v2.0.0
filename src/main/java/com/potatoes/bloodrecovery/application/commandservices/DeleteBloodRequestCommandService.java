package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class DeleteBloodRequestCommandService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional
    public void deleteBloodRequest(String cid, Long requestId) {
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(requestId)
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        if (bloodRequest.deletableBloodRequest()) {
            try {
                userRepository.requestPoint(cid, POINT_PLUS, 50 * bloodRequest.getBloodReqCnt());
                bloodRequest.deleteBloodRequest();
                bloodRequestRepository.save(bloodRequest);
            } catch (Exception e) {
                throw new ApiException(FAIL_DELETE_BLOOD_REQUEST);
            }

        } else {
            throw new ApiException(NOT_DELETE_BLOOD_REQUEST);
        }
    }
}
