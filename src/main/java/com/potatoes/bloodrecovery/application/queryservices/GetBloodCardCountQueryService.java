package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBloodCardCountQueryService {

    private final BloodCardRepository bloodCardRepository;

    @Transactional(readOnly = true)
    public Integer getBloodCardCount(String ci){
        List<BloodCard> cards = bloodCardRepository.findByCid(ci);

        if (cards.isEmpty()) {
            throw new ApiException(NO_BLOOD_CARD);
        }

        List<BloodCard> validCardList = new ArrayList<>();
        for (BloodCard card : cards) {
            if(card.isValidBloodCard()){
                validCardList.add(card);
            }
        }

        return validCardList.size();
    }
}
