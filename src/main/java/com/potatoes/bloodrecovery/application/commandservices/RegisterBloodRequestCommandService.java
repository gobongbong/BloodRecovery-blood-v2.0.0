package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodRequestCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterBloodRequestCommandService {

    @Transactional
    public void registerBloodRequest(RegisterBloodRequestCommand registerBloodRequestCommand){
        //todo 회원 닉네임 가지고 와야함

    }
}
