package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import com.potatoes.bloodrecovery.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCardList;
import static com.potatoes.constants.ResponseCode.NO_BLOOD_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetBloodCardCountQueryServiceTest {

    @InjectMocks
    GetBloodCardCountQueryService getBloodCardCountQueryService;

    @Mock
    private BloodCardRepository bloodCardRepository;

    @Test
    @DisplayName("헌혈증 개수 조회에 성공한다.")
    void getCardCount_success(){
        //given
        String cid = "aaaa";
        given(bloodCardRepository.findByCid(any())).willReturn(commonBloodCardList());

        int expect = 1;

        //when
        int result = getBloodCardCountQueryService.getBloodCardCount(cid);

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("헌혈증 개수 조회에 실패한다.")
    void getCardCount_fail(){
        //given
        String cid = "aaaa";
        List<BloodCard> cards = new ArrayList<>();
        given(bloodCardRepository.findByCid(any())).willReturn(cards);

        int expect = 1;

        //when
        Throwable throwable = assertThrows(ApiException.class, () -> getBloodCardCountQueryService.getBloodCardCount(cid));

        //then
        assertEquals(throwable.getMessage(), NO_BLOOD_CARD.getMessage());
    }
}