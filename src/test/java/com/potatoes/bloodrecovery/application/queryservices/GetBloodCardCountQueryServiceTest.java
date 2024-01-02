package com.potatoes.bloodrecovery.application.queryservices;

import com.potatoes.bloodrecovery.domain.repository.BloodCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.potatoes.bloodrecovery.mock.MockDataUtil.commonBloodCard;
import static org.assertj.core.api.Assertions.assertThat;
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
        given(bloodCardRepository.findByCid(any())).willReturn(commonBloodCard());

        int expect = 1;

        //when
        int result = getBloodCardCountQueryService.getBloodCardCount(cid);

        //then
        assertThat(result).isEqualTo(expect);
    }
}