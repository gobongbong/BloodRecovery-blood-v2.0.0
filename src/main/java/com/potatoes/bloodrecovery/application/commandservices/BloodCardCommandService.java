package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BloodCardCommandService {

    private final BloodCardRepository bloodCardRepository;

    public void registerBloodCard(RegisterBloodCardCommand registerBloodCardCommand){
        BloodCard bloodCard = new BloodCard(registerBloodCardCommand);
        bloodCardRepository.save(bloodCard);
    }
}
