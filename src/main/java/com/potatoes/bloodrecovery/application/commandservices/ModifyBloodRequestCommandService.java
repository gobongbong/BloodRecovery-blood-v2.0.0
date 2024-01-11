package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodRequest;
import com.potatoes.bloodrecovery.domain.model.commands.ModifyBloodRequestCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodRequestRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.NO_BLOOD_REQUEST;

@Service
@RequiredArgsConstructor
public class ModifyBloodRequestCommandService {

    private final BloodRequestRepository bloodRequestRepository;

    @Transactional
    public void modifyBloodRequest(ModifyBloodRequestCommand modifyBloodRequestCommand){
        BloodRequest bloodRequest = bloodRequestRepository.findByRequestId(modifyBloodRequestCommand.getRequestId());
        if (bloodRequest == null){
            throw new ApiException(NO_BLOOD_REQUEST);
        }

        if (bloodRequest.getPost().isModifiable()){
            bloodRequest.modifyBloodRequest(modifyBloodRequestCommand);
            bloodRequestRepository.save(bloodRequest);
        }
    }
}
