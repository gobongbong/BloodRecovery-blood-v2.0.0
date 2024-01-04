package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterBloodRequestCommandService {

    private final UserRepository userRepository;

    @Transactional
    public void registerBloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand){
        //todo 회원 닉네임 가져오기
        String nickName = userRepository.getUserInfo(registerBloodRequestCommand.getCid()).getNickname();

        BloodRequest bloodRequest = new BloodRequest(registerBloodRequestCommand, nickName);


    }
}
