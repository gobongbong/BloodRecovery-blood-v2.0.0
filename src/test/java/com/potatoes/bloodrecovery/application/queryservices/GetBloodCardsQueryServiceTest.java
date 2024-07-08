package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCard;
import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCardList;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        String ci = "1111";

        List<BloodCard> expect = new ArrayList<>();
        expect.add(commonBloodCard());

        given(bloodCardRepository.findByCid(any())).willReturn(commonBloodCardList());

        //when
        List<BloodCard> result = getBloodCardsQueryService.getBloodCards(ci);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }

    @Test
    @DisplayName("헌혈증 조회에 실패한다.")
    void getBloodCards_fail(){
        //given
        String ci = "1111";
        List<BloodCard> cards = new ArrayList<>();

        given(bloodCardRepository.findByCid(any())).willReturn(cards);

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> {
            getBloodCardsQueryService.getBloodCards(ci);
        });

        //then
        assertEquals(throwable.getMessage(), NO_BLOOD_CARD.getMessage());
    }
}