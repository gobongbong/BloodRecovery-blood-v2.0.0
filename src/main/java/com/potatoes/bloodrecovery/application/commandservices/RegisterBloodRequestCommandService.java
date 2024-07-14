package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import com.potatoes.constants.RequestStatus;
import com.potatoes.bloodrecovery.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.EXIST_BLOOD_REQUEST;
import static com.potatoes.constants.ResponseCode.FAIL_REGISTER_BLOOD_REQUEST;
import static com.potatoes.constants.StaticValues.POINT_MINUS;

@Service
@RequiredArgsConstructor
public class RegisterBloodRequestCommandService {

    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Transactional
    public void registerBloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand) {
        boolean isRequestExist = bloodRequestRepository.existsByCidAndRequestStatusIn(registerBloodRequestCommand.getCid(), RequestStatus.getOngoing());
        if (isRequestExist) {
            throw new ApiException(EXIST_BLOOD_REQUEST);
        }

        try {
            BloodRequest bloodRequest = new BloodRequest(registerBloodRequestCommand);

            bloodRequestRepository.save(bloodRequest);

            //요청한 헌혈증 개수 x 50 포인트 만큼 차감
//            userRepository.requestPoint(registerBloodRequestCommand.getCid(), POINT_MINUS, 50 * registerBloodRequestCommand.getBloodReqCnt());

        } catch (Exception e) {
            throw new ApiException(FAIL_REGISTER_BLOOD_REQUEST);
        }
    }
}
