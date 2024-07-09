package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.*;
import static com.potatoes.constants.StaticValues.POINT_MINUS;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class ModifyBloodRequestCommandService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional
    public void modifyBloodRequest(ModifyBloodRequestCommand modifyBloodRequestCommand) {
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(modifyBloodRequestCommand.getRequestId())
                .orElseThrow(() -> new ApiException(NO_BLOOD_REQUEST));

        if (bloodRequest.isModifiable()) {
            try {
                requestPoint(bloodRequest, modifyBloodRequestCommand);
                bloodRequest.modifyBloodRequest(modifyBloodRequestCommand);
                bloodRequestRepository.save(bloodRequest);
            } catch (Exception e) {
                throw new ApiException(FAIL_MODIFY_BLOOD_REQUEST);
            }
        } else {
            throw new ApiException(NOT_MODIFY_BLOOD_REQUEST);
        }
    }

    private void requestPoint(BloodRequest bloodRequest, ModifyBloodRequestCommand modifyBloodRequestCommand){
        Integer newBloodReqCnt = modifyBloodRequestCommand.getBloodReqCnt();

        if (bloodRequest.getBloodReqCnt() > newBloodReqCnt){
            userRepository.requestPoint(modifyBloodRequestCommand.getCid(), POINT_MINUS, 50 * (bloodRequest.getBloodReqCnt() - newBloodReqCnt));
        }
        if (bloodRequest.getBloodReqCnt() < newBloodReqCnt){
            userRepository.requestPoint(modifyBloodRequestCommand.getCid(), POINT_PLUS, 50 * (newBloodReqCnt - bloodRequest.getBloodReqCnt()));
        }
    }
}
