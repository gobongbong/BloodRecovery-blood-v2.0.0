package com.potatoes.bloodrecovery.application.commandservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCardHistory;
import com.potatoes.bloodrecovery.domain.model.commands.DeleteBloodCardCommand;
import com.potatoes.bloodrecovery.domain.repository.BloodCardHistoryRepository;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteBloodCardCommandService {

    private final BloodCardRepository bloodCardRepository;
    private final BloodCardHistoryRepository bloodCardHistoryRepository;

    @Transactional
    public void deleteBloodCard(DeleteBloodCardCommand deleteBloodCardCommand){

        BloodCard bloodCard = bloodCardRepository.findBloodCardByCustomerIdAndBloodCardId(deleteBloodCardCommand.getCustomerId(), deleteBloodCardCommand.getBloodCardId())
                .orElseThrow(() -> new ApiException(NO_BLOOD_CARD));
        deleteBloodCardCommand.setCardInfo(bloodCard);
        BloodCardHistory bloodCardHistory  = new BloodCardHistory(deleteBloodCardCommand);

        try {
            bloodCardRepository.deleteByBloodCardId(deleteBloodCardCommand.getBloodCardId());
            bloodCardHistoryRepository.save(bloodCardHistory);
        }catch (Exception e){
            log.error("헌혈증 삭제 처리 중 DB ERROR 발생 + {}", e);
            throw  e;
        }
    }
}
