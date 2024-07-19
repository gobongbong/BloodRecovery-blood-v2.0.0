package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.FAIL_REGISTER_CARD;
import static com.potatoes.constants.ResponseCode.NOT_VALID_CARD;

@Service
@RequiredArgsConstructor
public class BloodCardCommandService {

    private final BloodCardRepository bloodCardRepository;
    private final BloodCardHistoryRepository bloodCardHistoryRepository;

    @Transactional
    public void registerBloodCard(RegisterBloodCardCommand registerBloodCardCommand){

        BloodCard bloodCard = new BloodCard(registerBloodCardCommand);

        if ((bloodCard.isValidBloodCard())){
            try {
                bloodCardRepository.save(bloodCard);
                registerBloodCardCommand.setBloodCardId(bloodCard);
                BloodCardHistory bloodCardHistory = new BloodCardHistory(registerBloodCardCommand);
                bloodCardHistoryRepository.save(bloodCardHistory);
            } catch (Exception e){
                throw new ApiException(FAIL_REGISTER_CARD);
            }
        } else {
            throw new ApiException(NOT_VALID_CARD);
        }
    }
}
