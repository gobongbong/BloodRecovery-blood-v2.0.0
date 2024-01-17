package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.queries.GetBloodCardsQuery;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodCardsRspDto;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBloodCardsQueryService {

    private final BloodCardRepository bloodCardRepository;

    @Transactional(readOnly = true)
    public  List<BloodCard> getBloodCards(String cid){
        List<BloodCard> cards = bloodCardRepository.findByCid(cid);

        if (cards.isEmpty()) {
            throw new ApiException(NO_BLOOD_CARD);
        }

        return cards;
    }
}
