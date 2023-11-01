package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.model.queries.GetBloodCardsQuery;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.interfaces.rest.dto.GetBloodCardsRspDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetBloodCardsQueryServiceTest {

    @InjectMocks
    GetBloodCardsQueryService getBloodCardsQueryService;

    @Mock
    BloodCardRepository bloodCardRepository;

    @Test
    @DisplayName("헌혈증 조회에 성공한다.")
    void getBloodCards_success(){
        //given
        GetBloodCardsQuery getBloodCardsQuery = GetBloodCardsQuery.builder()
                .cid("aaaa")
                .build();
        List<BloodCard> cards = new ArrayList<>();
        BloodCard bloodCard = BloodCard.builder()
                .cid("aaaa")
                .code("1111")
                .date("20230201")
                .donationType("전혈")
                .name("고봉")
                .bloodCardId(1L)
                .build();
        cards.add(bloodCard);

        GetBloodCardsRspDto expect = GetBloodCardsRspDto.builder()
                .cards(cards)
                .build();

        given(bloodCardRepository.findByCid(any())).willReturn(cards);

        //when
        GetBloodCardsRspDto result = getBloodCardsQueryService.getBloodCards(getBloodCardsQuery);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }
}