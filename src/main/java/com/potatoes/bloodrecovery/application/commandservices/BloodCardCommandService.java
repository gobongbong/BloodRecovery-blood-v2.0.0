package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;
import com.potatoes.bloodrecovery.domain.model.commands.RegisterBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.constants.ResponseCode;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BloodCardCommandService {

    private final BloodCardRepository bloodCardRepository;
    private final BloodCardHistoryRepository bloodCardHistoryRepository;

    @Transactional
    public void registerBloodCard(RegisterBloodCardCommand registerBloodCardCommand){

        BloodCard bloodCard = new BloodCard(registerBloodCardCommand);
        registerBloodCardCommand.setBloodCardId(bloodCard);

        //todo event로 처리할지 고민
        BloodCardHistory bloodCardHistory = new BloodCardHistory(registerBloodCardCommand);

        if ((bloodCard.isValidBloodCard())){
            bloodCardRepository.save(bloodCard);
            bloodCardHistoryRepository.save(bloodCardHistory);
        } else {
            throw new ApiException(ResponseCode.NOT_VALID_CARD);
        }
    }
}
