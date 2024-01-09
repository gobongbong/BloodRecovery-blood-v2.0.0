package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.bloodrecovery.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyBloodRequestCommandService {

    private final UserRepository userRepository;
    private final BloodRequestRepository bloodRequestRepository;

    @Transactional
    public void modifyBloodRequest(ModifyBloodRequestCommand modifyBloodRequestCommand){
        //todo 헌혈 요청 개수는 헌혈증 기부자가 없는 경우에 수정 가능! 지정헌혈 정보도 이미 지정헌혈 지원자가 있는 경우에는 수정 불가!

    }
}
