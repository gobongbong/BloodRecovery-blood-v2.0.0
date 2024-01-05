package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterBloodRequestCommandService {

    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Transactional
    public void registerBloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand){
        String nickName = userRepository.getUserInfo(registerBloodRequestCommand.getCid()).getNickname();
        BloodRequest bloodRequest = new BloodRequest(registerBloodRequestCommand, nickName);

        bloodRequestRepository.save(bloodRequest);

        //todo 요청하는 헌혈증 개수 x 50 포인트만큼 차감
    }
}
