package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.FAIL_MODIFY_BLOOD_REQUEST;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_REQUEST;
import static com.potatoes.constants.StaticValues.POINT_MINUS;
import static com.potatoes.constants.StaticValues.POINT_PLUS;

@Service
@RequiredArgsConstructor
public class ModifyBloodRequestCommandService {

    private final BloodRequestRepository bloodRequestRepository;
    private final UserRepository userRepository;

    @Transactional
    public void modifyBloodRequest(ModifyBloodRequestCommand modifyBloodRequestCommand){
        Integer newBloodReqCnt = modifyBloodRequestCommand.getBloodReqCnt();

        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(modifyBloodRequestCommand.getRequestId());
        if (bloodRequest == null){
            throw new ApiException(NO_BLOOD_REQUEST);
        }

        try {
            if (bloodRequest.isModifiable()){
                if (bloodRequest.getBloodReqCnt() > newBloodReqCnt){
                    userRepository.requestPoint(modifyBloodRequestCommand.getCid(), POINT_MINUS, bloodRequest.getBloodReqCnt() - newBloodReqCnt);
                }
                if (bloodRequest.getBloodReqCnt() < newBloodReqCnt){
                    userRepository.requestPoint(modifyBloodRequestCommand.getCid(), POINT_PLUS, newBloodReqCnt - bloodRequest.getBloodReqCnt());
                }
                bloodRequest.modifyBloodRequest(modifyBloodRequestCommand);
                bloodRequestRepository.save(bloodRequest);
            }
        }catch (Exception e){
            throw new ApiException(FAIL_MODIFY_BLOOD_REQUEST);
        }
    }
}
