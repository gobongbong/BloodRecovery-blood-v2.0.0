package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.model.aggregates.BloodCard;
import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
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
        String ci = "1111";

        List<BloodCard> expect = new ArrayList<>();
        expect.add(commonBloodCard());

        given(bloodCardRepository.findByCid(any())).willReturn(commonBloodCardList());

        //when
        List<BloodCard> result = getBloodCardsQueryService.getBloodCards(ci);

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expect);
    }
}